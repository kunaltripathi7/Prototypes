
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void performTask() {
        try {
            // if(lock.tryLock()) {
            if (lock.tryLock(2, TimeUnit.SECONDS)) {

                try {
                    // do teh work
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    lock.unlock();
                }
            } else {
            System.out.println("if teh lock is busy do some other work");
        } // timed lock if 2 seconds in can't find gives up
        } catch (InterruptedException ex) {
            System.getLogger(TryLockExample.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }
}
