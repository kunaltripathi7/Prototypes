import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class KafkaController {


    private final Map<String, Topic> topics;
    private final Map<String, List<TopicConsumer>> topicConsumers;
    private final ExecutorService consumerExecutor;
    private final AtomicInteger topicIdCounter;


    public KafkaController() {
        topics = new ConcurrentHashMap<>();
        topicConsumers = new ConcurrentHashMap<>();
        // Using a cached thread pool to dynamically manage threads.
        consumerExecutor = Executors.newCachedThreadPool();
        topicIdCounter = new AtomicInteger(0);
    }

    public Topic createTopic(String topicName) {
        String topicId = String.valueOf(topicIdCounter.incrementAndGet());
        Topic topic = new Topic(topicId, topicName);
        topics.put(topicId, topic);
        topicConsumers.put(topicId, new CopyOnWriteArrayList<>());
        System.out.println("Created topic: " + topicName + " with id: " + topicId);
        return topic;
    }



    public void subscribe(IConsumer consumer, String topicId) {
        Topic topic = topics.get(topicId);
        if (topic == null) {
            System.err.println("Topic with id " + topicId + " does not exist");
            return;
        }
        TopicConsumer topicConsumer = new TopicConsumer(topic, consumer);
        List<TopicConsumer> consumers = topicConsumers.get(topicId);
        consumers.add(topicConsumer);
        consumerExecutor.submit(new TopicConsumerController(topicConsumer));
        topicConsumers.put(topicId, consumers);

        System.out.println(
                "Subscriber " + consumer.getId() + " subscribed to topic: " + topic.getTopicName());
    }



//            Thread.currentThread().interrupt(); // restore the interrupt flag because when excetpio is thrown then the flag is removed so for higher lvel code to know
    public void produce(IProducer producer, String topicId, Message message) {

        Topic topic = topics.get(topicId);
        if (topic == null) {
            throw new IllegalArgumentException("Topic with id " + topicId + " does not exist");
        }
        topic.addMessage(message);
        List<TopicConsumer> subs = topicConsumers.get(topicId);

        for (TopicConsumer topicConsumer : subs) {
            synchronized (topicConsumer) {
                topicConsumer.notify();
            }
        }
        System.out.println("Message " + message.getMessage());
    }

    public void resetOffset(String topicId, IConsumer consumer, int newOffset) {
        List<TopicConsumer> consumers = topicConsumers.get(topicId);
        if (consumers == null) {
            System.err.println("Topic with topicId " + topicId + "does not exist");
            return;
        }

        for (TopicConsumer tc : consumers) {
            if (tc.getConsumer().getId().equals(consumer.getId())) {
                tc.getOffSet().set(newOffset);
                synchronized (tc) { // same lock on TopicConsumer the lock which we are using in consumer Thread
                    tc.notify();
                }
                System.out.println("Offset for consumer " + consumer.getId() + "on topic " + tc.getTopic().getTopicName() + "reset to " + newOffset);
                break;
            }
        }

    }


    public void shutdown() {
        consumerExecutor.shutdown();
        try {
            if (!consumerExecutor.awaitTermination(5, TimeUnit.SECONDS)) {
                consumerExecutor.shutdownNow();
            }
        }
        catch (InterruptedException e) {
            consumerExecutor.shutdownNow();
        }
    }
}
