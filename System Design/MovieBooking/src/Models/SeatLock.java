package Models;

import java.time.LocalDateTime;

public class SeatLock {
    private final User lockedBy; // to check & verify user releasing the lock is same as user who applied it
    private final LocalDateTime lockTime;
    private final Seat seat;
    private final Show show;
    private int timeoutInSeconds;

    public SeatLock(User lockedBy, Integer timeoutInSeconds, Show show, Seat seat) {
        this.lockedBy = lockedBy;
        this.timeoutInSeconds = timeoutInSeconds;
        this.show = show;
        this.seat = seat;
        this.lockTime = LocalDateTime.now();
    }

    public Seat getSeat() {
        return seat;
    }

    public Show getShow() {
        return show;
    }

    public int getTimeoutInSeconds() {
        return timeoutInSeconds;
    }

    public void setTimeoutInSeconds(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(lockTime.plusSeconds(timeoutInSeconds));
    }

    public User getLockedBy() {
        return lockedBy;
    }

    public LocalDateTime getLockTime() {
        return lockTime;
    }

}
