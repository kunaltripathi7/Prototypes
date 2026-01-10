package Models;

import java.time.LocalDateTime;

public class Show {
    private final Screen screen;
    private final Movie movie;
    private final LocalDateTime startTime; //LocalDateTime.of(2026, 1, 10, 19, 30)
    private final Integer durationInMinutes;

    public Show(Screen screen, Movie movie, LocalDateTime startTime, int durationInMinutes) {
        this.screen = screen;
        this.movie = movie;
        this.startTime = startTime;
        this.durationInMinutes = durationInMinutes;
    }

    public Screen getScreen() {
        return screen;
    }

    public Movie getMovie() {
        return movie;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public int getDurationInMinutes() {
        return durationInMinutes;
    }
}
