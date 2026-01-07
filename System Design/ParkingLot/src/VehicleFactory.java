public class VehicleFactory {

    public static Vehicle createVehicle(VehicleType type, String licensePlate, PricingStrategy pricingStrategy) {
        switch (type) {
            case CAR:
                return new CarVehicle(licensePlate, pricingStrategy);
            default:
                return null;
        }
    }
}
