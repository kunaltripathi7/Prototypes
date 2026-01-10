public class Main {
    public static void main(String[] args) {
        KafkaController kafkaController = new KafkaController();
        // Create topics.
        Topic topic1 = kafkaController.createTopic("Topic1");
        Topic topic2 = kafkaController.createTopic("Topic2");

        // Create consumers.
        Consumer consumer1 = new Consumer("Subscriber1");
        Consumer consumer2 = new Consumer("Subscriber2");
        Consumer consumer3 = new Consumer("Subscriber3");
        // Subscribe: consumer1 subscribes to both topics,
        // consumer2 subscribes to topic1, and consumer3 subscribes to topic2.
        kafkaController.subscribe(consumer1, topic1.getTopicId());
        kafkaController.subscribe(consumer1, topic2.getTopicId());
        kafkaController.subscribe(consumer2, topic1.getTopicId());
        kafkaController.subscribe(consumer3, topic2.getTopicId());
        // Create producers.
        Producer producer1 = new Producer("Publisher1", kafkaController);
        Producer producer2 = new Producer("Publisher2", kafkaController);
        // Publish some messages.
        producer1.produce(topic1.getTopicId(), new Message("Message m1"));
        producer1.produce(topic1.getTopicId(), new Message("Message m2"));
        producer2.produce(topic2.getTopicId(), new Message("Message m3"));

        // Allow time for consumers to process messages.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer2.produce(topic2.getTopicId(), new Message("Message m4"));
        producer1.produce(topic1.getTopicId(), new Message("Message m5"));
        // Reset offset for consumer1 on topic1 (for example, to re-process messages).
        kafkaController.resetOffset(topic1.getTopicId(), consumer1, 0);
        // Allow some time before shutting down.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        kafkaController.shutdown();
    }
}
