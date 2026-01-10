package Models;

import java.util.ArrayList;
import java.util.List;

public class Theatre {
    private final List<Screen> screens = new ArrayList<>();
    private final String id;
    private String location;

    public Theatre(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public void createScreen(String id, int seatRows, int seatsPerRow) {
        Screen screen = new Screen(id, this);
        screens.add(screen);

    }

    public void deleteScreen(String id) {

    }

    public List<Screen> getScreens() {
        return screens;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
