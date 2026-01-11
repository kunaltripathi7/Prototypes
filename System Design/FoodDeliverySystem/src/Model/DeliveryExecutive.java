package Model;

public class DeliveryExecutive {
    private String name;
    private int rating;
    private int locationId;
    private final int id;

    public int getId() {
        return id;
    }

    public DeliveryExecutive(String name, int rating, int locationId, int id) {
        this.name = name;
        this.rating = rating;
        this.locationId = locationId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
