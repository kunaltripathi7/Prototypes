public class ParkingSpot {
    private VehicleType vehicleType;
    private int spotNumber;
    private boolean isOccupied;

    ParkingSpot(VehicleType vehicleType, int spotNumber) {
        this.vehicleType = vehicleType;
        this.spotNumber = spotNumber;
        this.isOccupied = false;
    }

    // getter setter

    // method for setting the occupied to true;

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getVehicleType() {
        return this.vehicleType;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void bookSpot() {
        if (isOccupied)
            throw new IllegalStateException("Spot " + spotNumber + " is already occupied");
        this.isOccupied = true;
    }

    public void freeSpot() {
        if (!isOccupied)
            throw new IllegalStateException("Spot " + spotNumber + " is already free");
        this.isOccupied = false;
    }
}
