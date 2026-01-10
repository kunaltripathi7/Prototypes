public class Consumer implements IConsumer{
    private String id;

    public Consumer(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return "";
    }

    @Override
    public void processMessage(Message message) throws InterruptedException {
        System.out.println("consumed the message");
        Thread.sleep(10000);
    }
}
