package Models;

import Enums.SeatType;

import java.util.ArrayList;
import java.util.List;

public class Screen {
    private final List<Seat> seats = new ArrayList<>();
    private final String id;
    private final Theatre theatre;

    public Screen(String id, Theatre theatre) {
        this.id = id;
        this.theatre = theatre;
    }

    public Seat addSeat(char rowNumber, SeatType seatType) {
       seats.add(new Seat(rowNumber, seats.size()+1, seatType));
       return seats.getLast();
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public String getId() {
        return id;
    }
}
