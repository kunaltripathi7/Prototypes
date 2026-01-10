public class TopicConsumerController implements Runnable{
    // code by interfaces execute by concrete classes
    private TopicConsumer topicConsumer;
    public TopicConsumerController(TopicConsumer topicConsumer) {
        this.topicConsumer = topicConsumer;
    }


    @Override
    public void run() {
        Topic topic = topicConsumer.getTopic();
        IConsumer consumer = topicConsumer.getConsumer();
        while(true) {
            Message messageToProcess = null;
            synchronized (topicConsumer) {
                while (topicConsumer.getOffSet().get() >= topic.getMessages().size()) {
                    try {
                        topicConsumer.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                int currentOffset = topicConsumer.getOffSet().getAndIncrement();
                messageToProcess = topic.getMessages().get(currentOffset);
            }
            try {
                consumer.processMessage(messageToProcess);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}


//  multiple threads have to coordinate on the topicConsumer -> like There will be multiple threads of TopicConsumerController -> and they all will be using topicConsumer(thread monitor) to communitcate, there can be mulitple threads for this controller

// -> topic -> consumer1 -> TopicConsumerController (one instance)         now two threads are waiting to read from a topic as soon as it produced. each has its own offset.
//       | -->  consumer 2 -> TopicConsumerController (second instance)



//while(true) {
//  - Is there a new message?
//    - NO → sleep/wait
//    - YES → grab message, give it to subscriber, repeat
//} why that specific object (topicSubscriber) -> because producer notifies that