import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.Semaphore;

public class BoundedBlockingQueue {
    private final Semaphore consumer = new Semaphore(0);;
    private final Semaphore producer;
    private Deque<Integer> deque;

    public BoundedBlockingQueue(int capacity) {
        deque = new ConcurrentLinkedDeque<>();
        producer = new Semaphore(capacity);
    }


    public void enqueue(int number) throws InterruptedException {
        producer.acquire();
        deque.add(number);
        consumer.release();
    }

    public void getEle() throws InterruptedException {
        int result = -1;
        consumer.acquire();
        result = deque.poll();
        producer.release();
    }
}
