import java.util.concurrent.atomic.AtomicInteger;

public class TopicConsumer {
        private Topic topic;
        private IConsumer consumer;
        private AtomicInteger offSet;

        public TopicConsumer(Topic topic, IConsumer consumer) {
            this.topic = topic;
            this.consumer = consumer;
            this.offSet = new AtomicInteger(0);
        }

        public Topic getTopic() {
            return topic;
        }

        public IConsumer getConsumer() {
            return consumer;
        }

        public AtomicInteger getOffSet () {
            return offSet;
        }
}
