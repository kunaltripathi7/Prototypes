package Service;

import Models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {
    private final ISeatLockProvider seatLockProvider;
    private final Map<String, Booking> showBookings = new ConcurrentHashMap<>(); // Initialize this!
    private final AtomicInteger bookingIdCounter = new AtomicInteger(0); // Initialize this!

    // Constructor Injection
    public BookingService(ISeatLockProvider seatLockProvider) {
        this.seatLockProvider = seatLockProvider;
    }

    public Booking createBooking(Show show, List<Seat> seats, User user) throws Exception {
        // 1. Check if already booked (permanently)
        if (isAnySeatAlreadyBooked(show, seats)) {
            throw new Exception("Seat Already Booked");
        }
        
        // 2. Try to lock seats (temporarily)
        // This will throw exception if locked by someone else
        seatLockProvider.lockSeats(seats, show, user);
        
        // 3. Create Booking
        final String bookingId = String.valueOf(bookingIdCounter.getAndIncrement());
        final Booking newBooking = new Booking(bookingId, user, seats, show);
        
        showBookings.put(bookingId, newBooking);
        return newBooking;
    }

    public void confirmBooking(Booking booking, User user) throws Exception {
        if (!booking.getUser().equals(user)) {
            throw new Exception("Cannot confirm a booking made by another user");
        }
        
        // In a real system, you would verify the lock is still valid here
        // e.g., if (seatLockProvider.validateLock(booking.getSeats(), ...))
        
        booking.confirmBooking();
    }

    public List<Booking> getAllBookings(Show show) {
        List<Booking> response = new ArrayList<>();
        for (Booking booking : showBookings.values()) {
            if (booking.getShow() == show) response.add(booking);
        }
        return response;
    }

    public List<Seat> getBookedSeats(Show show) {
        List<Booking> bookings = getAllBookings(show);
        List<Seat> seats = new ArrayList<>();
        for (Booking booking : bookings) {
            if (booking.isBooked()) { // Only return CONFIRMED seats
                seats.addAll(booking.getSeats());
            }
        }
        return seats;
    }

    private boolean isAnySeatAlreadyBooked(final Show show, final List<Seat> seats) {
        final List<Seat> bookedSeats = getBookedSeats(show);
        for (Seat seat : seats) {
            if (bookedSeats.contains(seat)) return true;
        }
        return false;
    }
}
