// problem -> producer -consumer -> thread communication

// [0,1,2,3,4] -> producer will wait when q is full/ consumer will wait when its empty

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {

    private final Queue<Integer> buffer = new LinkedList<>();;
    private final int bufferSize = 5;


    public void produce() throws InterruptedException {
        int count = 0;
        while (true) {
            synchronized (this) {
                while (buffer.size() ==  bufferSize) {  // why? take this scenario -> p1 waits() next time it will wake up it will resume from the next line so it must recheck the condition.
                    wait();
                }
                    buffer.offer(count++);
                    System.out.println("produced");
                    notifyAll();
            }
        Thread.sleep(1000);
        }
    }

    public void consume() throws InterruptedException {
        while (true) {
            synchronized (this) {
                 while (buffer.isEmpty()) {
                 wait();
                 }
                    buffer.poll();
                    System.out.println("consumed");
                    notifyAll();
            }
            Thread.sleep(1500);
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Thread producerThread = new Thread(() -> {
            try {
                producerConsumer.produce();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumerThread = new Thread(() -> {
            try {
                producerConsumer.consume();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        producerThread.start();
        consumerThread.start();
    }
}
