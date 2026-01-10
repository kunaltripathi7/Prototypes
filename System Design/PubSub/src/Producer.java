public class Producer implements IProducer{
    private final String id;
    private KafkaController kafkaController;

    public Producer(String id, KafkaController controller) {
        this.id = id; this.kafkaController = controller;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void produce( String topicId, Message message) {
        System.out.println("Sent to controller to produce");
        kafkaController.produce(this, topicId, message);
    }
}
