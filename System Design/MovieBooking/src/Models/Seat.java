package Models;

import Enums.SeatType;

public class Seat {
    private char row;
    private int number;
    private SeatType seat;

    public Seat(char row, int number, SeatType seat) {
        this.row = row;
        this.number = number;
        this.seat = seat;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public SeatType getSeat() {
        return seat;
    }

    public void setSeat(SeatType seat) {
        this.seat = seat;
    }
}
