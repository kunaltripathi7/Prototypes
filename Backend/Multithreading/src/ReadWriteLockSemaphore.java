import java.util.concurrent.Semaphore;

public class ReadWriteLockSemaphore {
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore wrtLock = new Semaphore(1);
    private int readerCount = 0;

    public void acquireReadLock() throws InterruptedException {
        mutex.acquire();
        readerCount++;
        if (readerCount == 1) {
            wrtLock.acquire();
        }
        mutex.release();
    }

    public void releaseReadLock() throws InterruptedException {
        mutex.acquire();
        readerCount--;
        if (readerCount == 0) {
            wrtLock.release();
        }
        mutex.release();
    }

    public void acquireWriteLock() throws InterruptedException {
        wrtLock.acquire();
    }

    public void releaseWriteLock() throws InterruptedException {
        wrtLock.release();
    }




    public static void main(String[] args) {
        ReadWriteLockSemaphore rw = new ReadWriteLockSemaphore();
        Runnable thread1 = () -> {
            try {
                rw.acquireReadLock();
                System.out.println("Reading");
                Thread.sleep(10000);
                System.out.println("REading completed");
                rw.releaseReadLock();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted");
            }
        };

        Runnable thread2 = () -> {
            try {
                rw.acquireWriteLock();
                System.out.println("Writing");
                Thread.sleep(10000);
                System.out.println("Writing completed");
                rw.releaseWriteLock();
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted");
            }
        };
        Thread readThread = new Thread(thread1);
        Thread writeThread = new Thread(thread2);
        readThread.start();
        writeThread.start();
    }
}
