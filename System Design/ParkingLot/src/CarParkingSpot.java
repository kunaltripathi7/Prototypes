public class CarParkingSpot extends ParkingSpot {

    public CarParkingSpot(int spotNumber) {
        // Pass VehicleType.CAR directly to the parent constructor
        super(spotNumber, VehicleType.CAR);
    }

    @Override
    boolean canPark(VehicleType vehicleType) {
        return vehicleType == VehicleType.CAR;
    }
}
