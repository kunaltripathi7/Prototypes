import Enums.SeatType;
import Models.*;
import Service.BookingService;
import Service.SeatLockProvider;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 1. Setup Data
        Theatre theatre = new Theatre("T1", "Downtown");
        theatre.createScreen("Screen1", 10, 10); // Create screen
        Screen screen = theatre.getScreens().get(0);
        
        // Add seats to screen
        Seat seat1 = screen.addSeat('A', SeatType.SILVER);
        Seat seat2 = screen.addSeat('A', SeatType.SILVER);
        Seat seat3 = screen.addSeat('A', SeatType.SILVER);

        Movie movie = new Movie("Inception", 148);
        Show show = new Show(screen, movie, LocalDateTime.now().plusHours(2), 148);

        // 2. Setup Services
        SeatLockProvider seatLockProvider = new SeatLockProvider(100); // 100s timeout
        BookingService bookingService = new BookingService(seatLockProvider); 

        // 3. Create Users
        User user1 = new User(1, "Alice");
        User user2 = new User(2, "Bob");
        User user3 = new User(3, "Charlie");

        // 4. Define the seats everyone wants to fight for
        List<Seat> targetSeats = new ArrayList<>();
        targetSeats.add(seat1);
        targetSeats.add(seat2);

        // 5. Simulate Concurrency
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Task for User 1
        executor.submit(() -> {
            attemptBooking(bookingService, user1, show, targetSeats);
        });

        // Task for User 2
        executor.submit(() -> {
            attemptBooking(bookingService, user2, show, targetSeats);
        });
        
        // Task for User 3
        executor.submit(() -> {
            attemptBooking(bookingService, user3, show, targetSeats);
        });

        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        
        System.out.println("\n--- Final Booking Status ---");
        List<Booking> allBookings = bookingService.getAllBookings(show);
        for (Booking b : allBookings) {
            // Check actual status
            String status = b.isBooked() ? "CONFIRMED" : "CREATED/EXPIRED";
            System.out.println("Booking ID: " + b.getId() + " | User: " + b.getUser().getName() + " | Status: " + status);
        }
    }

    private static void attemptBooking(BookingService bookingService, User user, Show show, List<Seat> seats) {
        System.out.println(user.getName() + " is attempting to book...");
        try {
            Booking booking = bookingService.createBooking(show, seats, user);
            System.out.println("SUCCESS: " + user.getName() + " created booking " + booking.getId());
            
            // Simulate payment processing time
            Thread.sleep(100); 
            
            bookingService.confirmBooking(booking, user);
            System.out.println("CONFIRMED: " + user.getName() + " confirmed booking " + booking.getId());
            
        } catch (Exception e) {
            System.out.println("FAILED: " + user.getName() + " failed. Reason: " + e.getMessage());
        }
    }
}
