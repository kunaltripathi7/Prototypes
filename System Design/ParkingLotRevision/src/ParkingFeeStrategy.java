public interface ParkingFeeStrategy {
    double calculateFee(int duration, VehicleType vehicleType);
}
