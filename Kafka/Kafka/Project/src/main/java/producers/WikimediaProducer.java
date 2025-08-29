package producers;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;


public class WikimediaProducer {
    private static final Logger log = LoggerFactory.getLogger(WikimediaProducer.class.getSimpleName());

    public static void main(String[] args) throws Exception {
        log.info("Wikimedia Producer");

        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "172.22.242.137:9092");


        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);
        String topic = "wikimedia.recentchange";
        AtomicInteger counter = new AtomicInteger(0);

            HttpClient client = HttpClient.newBuilder().build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://stream.wikimedia.org/v2/stream/recentchange"))
                    .header("User-Agent", "MyKafkaProducer/1.0 (ikunaltripathi@email.com)")
                    .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofLines())
                    .thenAccept(response -> response.body().forEach(line -> {
                        try {
                            // Skip empty or comment lines
                            if (line.isEmpty() || line.startsWith(":")) return;

                            String key = "id_" + counter.getAndIncrement();
                            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, line);

                            producer.send(record, (metadata, e) -> {
                                if (e == null) {
                                    log.info("Key: " + key + " | Partition: " + metadata.partition());
                                } else {
                                    log.error("Error while producing", e);
                                }
                            });

                        } catch (Exception e) {
                            log.error("Exception in line processing", e);
                        }
                    }));
        Thread.sleep(10 * 60 * 1000);

        producer.close();
        log.info("Producer closed");
    }
}
