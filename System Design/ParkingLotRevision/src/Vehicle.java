public abstract class Vehicle {
    protected int licensePlate;
    protected VehicleType vehicleType;
    protected ParkingFeeStrategy parkingFeeStrategy;

    public Vehicle(int licensePlate, VehicleType vehicleType, ParkingFeeStrategy parkingFeeStrategy) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public int getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(int licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ParkingFeeStrategy getParkingFeeStrategy() {
        return parkingFeeStrategy;
    }

    public void setParkingFeeStrategy(ParkingFeeStrategy parkingFeeStrategy) {
        this.parkingFeeStrategy = parkingFeeStrategy;
    }

    public double calculateFee(int duration) {
        return  parkingFeeStrategy.calculateFee(duration, this.vehicleType);
    }
}
