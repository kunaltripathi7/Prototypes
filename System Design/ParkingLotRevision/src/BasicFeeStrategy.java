public class BasicFeeStrategy implements ParkingFeeStrategy{
    @Override
    public double calculateFee(int durationInHours, VehicleType vehicleType) {
        return switch (vehicleType) {
            case TRUCK -> 50 * durationInHours;
            case BIKE -> 20 * durationInHours;
            case CAR -> 40 * durationInHours;
        };
    }
}
