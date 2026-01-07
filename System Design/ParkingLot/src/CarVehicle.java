public class CarVehicle extends Vehicle{

    public CarVehicle(String licensePlate, PricingStrategy pricingStrategy) {
        super(licensePlate, VehicleType.CAR, pricingStrategy);
    }
}
