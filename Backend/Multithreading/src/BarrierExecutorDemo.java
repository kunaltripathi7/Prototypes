import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


public class BarrierExecutorDemo {

    static class SemaphoreBarrier {
        private final int parties;
        private int count;
        private final Semaphore mutex = new Semaphore(1);
        private final Semaphore barrier = new Semaphore(0);

        public SemaphoreBarrier(int parties) {
            this.count = parties;
            this.parties = parties;
        }

        public void await() throws InterruptedException {
            mutex.acquire();
            count--;
            if (count == 0) {
                System.out.println("althread completed proceeding to next round");
                barrier.release(parties-1);
                count = parties;
                mutex.release();
            }
            else {
                mutex.release();
                barrier.acquire(); // thread are waiting over here. if last thread comes then it frees all of them.
            }
        }


    }


    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        BarrierExecutorDemo.SemaphoreBarrier barrier = new SemaphoreBarrier(5);

        for (int i=0; i<5; i++) {
            executor.submit( () -> {
                try {
                    Thread.sleep(1000);
                    System.out.println("process 1 completed");
                    barrier.await();
                    System.out.println("carried to process2");
                    Thread.sleep(1000);
                    barrier.await();
                }
                catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                    System.out.println("Current thread interrupted");
                }
            });
        }
    }
}
