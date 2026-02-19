public abstract class Vehicle { // why abstract? because we want to have shared state and prevent instantiation,
                                // abstract class main job -> prevent instantiation
    protected String vehicleNumber;
    protected VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    // getter
    // setter

}