package Models;

import java.util.List;
import java.util.Map;

public interface ISeatLockProvider {
     void lockSeats(List<Seat> seats, Show show, User lockedBy) throws Exception;
     void releaseSeats(List<Seat> seats, Show show, User lockedBy) throws Exception;
     List<Seat> getLockedSeats(Show show);

}
