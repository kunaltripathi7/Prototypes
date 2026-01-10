package Models;

import java.time.LocalDateTime;

public class ShowSeat {
    private Show show;
    private Seat seat;
    private LocalDateTime startTime;
    private int durationInMinutes;

    public ShowSeat(Show show, Seat seat, LocalDateTime startTime, int durationInMinutes) {
        this.show = show;
        this.seat = seat;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
