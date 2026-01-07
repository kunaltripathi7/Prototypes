import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    public List<ParkingSpot> spots;
    public int floorNumber;

    public ParkingFloor(List<ParkingSpot> spots, int floorNumber) {
        if (spots == null) {
            this.spots = new ArrayList<>();
        }
        else this.spots = spots;
        this.floorNumber = floorNumber;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (vehicle == null) {
            throw new IllegalArgumentException("Vehicle cannot be null");
        }
        
        for (ParkingSpot spot : spots) {
            if (!spot.isOccupied()) {
                if (spot.parkVehicle(vehicle)) {
                    System.out.println("Vehicle " + vehicle.getType() + " parked at Floor " + floorNumber + " Spot " + spot.getSpotNumber());
                    return true;
                }
            }
        }
        return false; // No suitable spot found on this floor
    }

    public void addSpot(ParkingSpot spot) {
        this.spots.add(spot);
    }
}
