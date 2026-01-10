package Models;

import java.time.LocalDateTime;

public class Movie {
    private String movieName;
    private int durationInMinutes;

    public Movie(String movieName, int durationInMinutes) {
        this.movieName = movieName;
        this.durationInMinutes = durationInMinutes;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }
}
