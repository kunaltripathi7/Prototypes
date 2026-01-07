import java.util.ArrayList;
import java.util.List;

abstract public class ParkingSpot {
    private Vehicle currentVehicle; // Store the actual vehicle
    private boolean isOccupied;
    private int spotNumber;
    private VehicleType allowedType; // Changed from SpotType to VehicleType as discussed

    public ParkingSpot(int spotNumber, VehicleType allowedType) {
        this.allowedType = allowedType;
        this.isOccupied = false;
        this.spotNumber = spotNumber;
    }

    // Check if the specific spot implementation allows this vehicle type
    abstract boolean canPark(VehicleType vehicleType);

    public boolean parkVehicle(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }
        if (!canPark(vehicle.getType())) {
            return false;
        }
        
        this.currentVehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public void removeVehicle() {
        this.currentVehicle = null;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public VehicleType getAllowedType() {
        return allowedType;
    }
    
    public Vehicle getVehicle() {
        return currentVehicle;
    }
}
