package Models;

import Enums.BookingStatus;

import java.util.List;

public class Booking {
    private final String id;
    private final User user;
    private final List<Seat> seats;
    private BookingStatus status; // owns this
    private final Show show;

    public Booking(String id, User user, List<Seat> seats, Show show) {
        this.id = id;
        this.user = user;
        this.seats = seats;
        this.show = show;
        this.status = BookingStatus.CREATED;
    }

    public boolean isBooked() {
        return status == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() throws Exception {
        if (this.status != BookingStatus.CREATED) { // Fixed logic: Only allow confirmation if currently CREATED
            throw new Exception("Booking is not in CREATED state, cannot confirm.");
        }
        this.status = BookingStatus.CONFIRMED;
    }

    public void expireBooking() throws Exception {
        if (this.status != BookingStatus.CREATED) throw new Exception("can't expire booking");
        this.status = BookingStatus.EXPIRED;
    }

    public String getId() {
        return id;
    }


    public User getUser() {
        return user;
    }


    public List<Seat> getSeats() {
        return seats;
    }

    public Show getShow() {
        return show;
    }
}
