public class VehicleFactory {
    public static Vehicle getVehicle(VehicleType vehicleType, String licensePlate, ParkingFeeStrategy strategy) {
        c static Vehicle createVehicle(String vehicleType, String licensePlate, ParkingFeeStrategy feeStrategy) {
            if (vehicleType.equalsIgnoreCase("Car")) {
                return new CarVehicle(licensePlate, vehicleType, feeStrategy);
            } else if (vehicleType.equalsIgnoreCase("Bike")) {
                return new BikeVehicle(licensePlate, vehicleType, feeStrategy);
            }
            return new OtherVehicle(licensePlate, vehicleType, feeStrategy); // For un
    }
}
