public interface IConsumer {
    String getId();
    void processMessage(Message message) throws InterruptedException;
}
