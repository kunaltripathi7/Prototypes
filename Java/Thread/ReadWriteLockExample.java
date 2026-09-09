import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();
    private final ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();
    
    private String sharedData = "Initial";

    public void readData(String readerName) {
        readLock.lock(); // Multiple threads can hold this lock at the same time!
        try {
            System.out.println(readerName + " is READING. Data: " + sharedData);
            Thread.sleep(1000); // Simulate reading time.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            readLock.unlock();
            System.out.println(readerName + " finished reading.");
        }
    }

    public void writeData(String writerName, String newData) {
        writeLock.lock(); // ONLY ONE thread can hold this, and NO readers allowed!
        try {
            System.out.println(">>>>> " + writerName + " is WRITING. Updating data...");
            sharedData = newData;
            Thread.sleep(2000); // Simulate writing time.
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
            System.out.println(">>>>> " + writerName + " finished writing.");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReadWriteLockExample example = new ReadWriteLockExample();

        // 3 Reader threads start together. They will all run simultaneously.
        for (int i = 1; i <= 3; i++) {
            final int id = i;
            new Thread(() -> example.readData("Reader-" + id)).start();
        }

        Thread.sleep(500); // Let readers start.

        // Writer tries to start. It will wait for Reader-1,2,3 to finish.
        new Thread(() -> example.writeData("Writer-1", "NewValue")).start();

        // Another Reader tries to start while Writer is waiting/holding lock.
        // This reader will be BLOCKED until Writer finishes!
        Thread.sleep(100);
        new Thread(() -> example.readData("Reader-4")).start();
    }
}