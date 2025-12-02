package consumers;

import com.google.gson.JsonParser;
import org.apache.hc.client5.http.auth.AuthScope;
import org.apache.hc.client5.http.auth.UsernamePasswordCredentials;
import org.apache.hc.client5.http.impl.DefaultConnectionKeepAliveStrategy;
import org.apache.hc.client5.http.impl.auth.BasicCredentialsProvider;
import org.apache.hc.core5.http.HttpHost;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.opensearch.action.bulk.BulkRequest;
import org.opensearch.action.bulk.BulkResponse;
import org.opensearch.action.index.IndexRequest;
import org.opensearch.action.index.IndexResponse;
import org.opensearch.client.*;
import org.opensearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OpenSearchConsumer {

    // single logger instance for this class
    private static final Logger logger = LoggerFactory.getLogger(OpenSearchConsumer.class);

    public static RestHighLevelClient createOpenSearchClient() {
        String connString = System.getenv("OPENSEARCH_URL");
        if (connString == null || connString.isBlank()) {
            connString = "http://localhost:9200";
        }

        URI connUri = URI.create(connString);
        String scheme = connUri.getScheme() == null ? "http" : connUri.getScheme();
        String host = connUri.getHost() == null ? "localhost" : connUri.getHost();
        int port = connUri.getPort() == -1 ? ("https".equalsIgnoreCase(scheme) ? 443 : 9200) : connUri.getPort();

        String userInfo = connUri.getUserInfo();
        if (userInfo == null) {
            return new RestHighLevelClient(org.opensearch.client.RestClient.builder(new HttpHost(scheme, host, port)));
        } else {
            String[] auth = userInfo.split(":", 2);
            BasicCredentialsProvider cp = new BasicCredentialsProvider();
            cp.setCredentials(new AuthScope(host, port),
                    new UsernamePasswordCredentials(auth[0], auth.length > 1 ? auth[1].toCharArray() : new char[0]));

            return new RestHighLevelClient(
                    RestClient.builder(new HttpHost(scheme, host, port))
                            .setHttpClientConfigCallback(
                                    httpAsyncClientBuilder -> httpAsyncClientBuilder
                                            .setDefaultCredentialsProvider(cp)
                                            .setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy()))
            );
        }
    }

    // ensure index exists using low-level REST calls to avoid client-added incompatible params
    private static void ensureIndexExists(RestHighLevelClient client, String index) throws IOException {
        RestClient low = client.getLowLevelClient();
        Request head = new Request("HEAD", "/" + index);
        try {
            Response resp = low.performRequest(head);
            if (resp.getStatusLine().getStatusCode() == 200) {
                logger.info("Index '{}' already exists.", index);
                return;
            }
        } catch (ResponseException e) {
            int status = e.getResponse().getStatusLine().getStatusCode();
            if (status != 404) {
                // rethrow unexpected statuses
                throw e;
            }
            // status == 404 -> create index
        }

        // create index with empty body (server will create default settings/mappings)
        Request put = new Request("PUT", "/" + index);
        put.setJsonEntity("{}");
        Response createResp = low.performRequest(put);
        logger.info("Create index response: {} {}", createResp.getStatusLine().getStatusCode(), createResp.getStatusLine().getReasonPhrase());
    }



    //static first approach
    private static KafkaConsumer<String, String> createKafkaConsumer(){

        String groupId = "consumer-opensearch-demo";

        // create consumer configs
        Properties properties = new Properties();
        properties.setProperty(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, "172.22.242.137:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");

        // create consumer
        return new KafkaConsumer<>(properties);

    }

    private static String extractId(String json){
        // gson library
        return JsonParser.parseString(json)
                .getAsJsonObject()
                .get("meta")
                .getAsJsonObject()
                .get("id")
                .getAsString();
    }

    public static void main(String[] args) throws IOException {

        RestHighLevelClient client = createOpenSearchClient();
        KafkaConsumer<String, String> consumer = createKafkaConsumer();

        consumer.subscribe(Collections.singleton("wikimedia.recentchange"));

        final Thread mainThread = Thread.currentThread();
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void  run() {
                logger.info("Detected a shutdown, let's exit by calling consumer.wakeup(//");
                consumer.wakeup();
                try {
                    mainThread.join();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        try (client; consumer) {
            ensureIndexExists(client, "wikimedia");
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(3000));
                int recordCount = records.count();
                logger.info("Received " + String.valueOf(recordCount) + "records");
                BulkRequest bulkRequest = new BulkRequest();
                for (ConsumerRecord<String, String> record : records) {
                    String id = extractId(record.value());
                    IndexRequest indexRequest = new IndexRequest("wikimedia").source(record.value(), XContentType.JSON).id(id);

//                    IndexResponse response = client.index(indexRequest, RequestOptions.DEFAULT);
                    bulkRequest.add(indexRequest);
//                    logger.info(response.getId());
                }

                if (bulkRequest.numberOfActions() > 0) {
                    BulkResponse response = client.bulk(bulkRequest, RequestOptions.DEFAULT);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    consumer.commitSync();
                    logger.info("Offsets have been committed");

                }
            }
        } catch (IOException e) {
            logger.error("Error communicating with OpenSearch", e);
            System.exit(1);
        }   catch (WakeupException e) {
            logger.info("Consumer is starting to shut down");
        } catch (Exception e) {
            logger.error("Unexpected exception in the consumer", e);
        } finally {
            consumer.close(); // close the consumer, this will also commit offsets
            logger.info("The consumer is now gracefully shut down");
        }



    }
}
