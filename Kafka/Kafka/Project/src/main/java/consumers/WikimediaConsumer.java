package consumers;

import org.opensearch.action.index.IndexRequest;
import org.opensearch.client.RequestOptions;
import org.opensearch.client.RestClient;
import org.opensearch.client.RestHighLevelClient;
import org.apache.http.HttpHost;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.opensearch.common.xcontent.XContentType;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class WikimediaConsumer {
    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.22.242.137:9092");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "my-wikimedia-consumer");
        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singleton("wikimedia.recentchange"));

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")
                )
        );
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Shutting down: ");
            consumer.close();
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));


        while(true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Key " + record.key() + ", Value: " + record.value());
                String jsonValue = record.value();
                if (jsonValue != null && jsonValue.trim().startsWith("{")) {
                    JsonObject obj = JsonParser.parseString(jsonValue).getAsJsonObject();
                    IndexRequest request = new IndexRequest("wikimedia-changes").source(record.value(), XContentType.JSON);
                    client.index(request, RequestOptions.DEFAULT);
                    System.out.println("Title: " + obj.get("title").getAsString());
                } else {
                    System.out.println("Skipping non json " + jsonValue);
                }
            }
        }

    }
}
