package Service;

import Enums.BookingStatus;
import Models.ISeatLockProvider;
import Models.Seat;
import Models.Show;

import java.util.List;

public class SeatAvailabilityService {
    private final ISeatLockProvider lockProvider;
    private final BookingService bookingService;
//    why no show ? -> because it would have defeated the purpose of this service. it would have been restricted to one show.

    public SeatAvailabilityService(ISeatLockProvider lockProvider, BookingService bookingService) {
        this.lockProvider = lockProvider;
        this.bookingService = bookingService;
    }

    public List<Seat> getAvailableSeats(Show show) {
        // all seats
        List<Seat> allSeats = show.getScreen().getSeats();
        List<Seat> unavailableSeats = getUnavailableSeats(show);
        allSeats.removeAll(unavailableSeats);
        return allSeats;
    }

    public List<Seat> getUnavailableSeats(Show show) {
        List<Seat> lockedSeats = bookingService.getBookedSeats(show);
        lockedSeats.addAll(lockProvider.getLockedSeats(show));
        return lockedSeats;
    }

    public ISeatLockProvider getLockProvider() {
        return lockProvider;
    }

}
