import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;
    private int carSpots;
    private int bikeSpots;
    private int otherSpots;
    
    // Main entry point for parking
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingFloor floor : floors) {
            if (floor.parkVehicle(vehicle)) {
                return true; // Successfully parked
            }
        }
        System.out.println("Parking Lot is Full for " + vehicle.getType());
        return false;
    }

    private ParkingLot(Builder builder) {
        this.floors = builder.floors;
        this.carSpots = builder.carSpots;
        this.bikeSpots = builder.bikeSpots;
        this.otherSpots = builder.otherSpots;
    }

    public static class Builder {
        private final List<ParkingFloor> floors;
        private int carSpots;
        private int bikeSpots;
        private int otherSpots;

        public Builder() {
            this.floors = new ArrayList<>();
        }

        public Builder createNewFloor(int carSpots, int bikeSpots, int otherSpots, int floorNumber) {
            int offSet = 0;
            List<ParkingSpot> parkingSpots = new ArrayList<>();
            
            // Add Car Spots
            for (int i = 0; i < carSpots; i++) {
                parkingSpots.add(new CarParkingSpot(offSet + i));
            }
            offSet += carSpots;
            
            // You can add loops here for Bike/Truck spots similarly
            
            ParkingFloor parkingFloor = new ParkingFloor(parkingSpots, floorNumber);
            floors.add(parkingFloor);
            return this;
        }

        public ParkingLot build() {
            return new ParkingLot(this);
        }
    }
}
