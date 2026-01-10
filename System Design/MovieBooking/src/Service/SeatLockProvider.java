package Service;

import Models.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class SeatLockProvider implements ISeatLockProvider {
    private final Map<Show, Map<Seat, SeatLock>> locks = new ConcurrentHashMap<>();
    private final int timeoutInSeconds;

    public SeatLockProvider(int timeoutInSeconds) {
        this.timeoutInSeconds = timeoutInSeconds;
    }


    @Override
    public void lockSeats(List<Seat> seats, Show show, User lockedBy) throws Exception {
        Map<Seat, SeatLock> seatLocks = locks.computeIfAbsent(show, s -> new ConcurrentHashMap<>());
        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (seatLocks.containsKey(seat)) {
                    SeatLock existingLcok = seatLocks.get(seat);
                    if (!existingLcok.isExpired()) throw new Exception("humesha der kar deta hu main.");
                }
            }

            for (Seat seat : seats) {
                SeatLock lock = new SeatLock(lockedBy, timeoutInSeconds, show, seat );
                seatLocks.put(seat, lock);
            }
        }
    }

    @Override
    public void releaseSeats(List<Seat> seats, Show show, User lockedBy) throws Exception {
        Map<Seat, SeatLock> seatLocks = locks.get(show);
        if (seatLocks == null) return;
        synchronized (seatLocks) {
            for (Seat seat : seats) {
                if (!seatLocks.containsKey(seat)) throw new Exception("No seatlocks found");
                if (!validateLock(lockedBy, seatLocks.get(seat))) throw new Exception("fdasfadsf");
            }
            for (Seat seat : seats) {
                seatLocks.remove(seat);
            }
        }
    }

    public boolean validateLock(User lockedBy, SeatLock seatLock) {
        return seatLock.getLockedBy() == lockedBy;
    }

    @Override
    public List<Seat> getLockedSeats(Show show) {
        Map<Seat, SeatLock> seatLocks =  locks.get(show);
        if (seatLocks == null) return Collections.emptyList();
        synchronized (seatLocks) {
            return seatLocks.entrySet().stream().filter(entry -> !entry.getValue().isExpired())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList()); // practice again
        }
    }
}
