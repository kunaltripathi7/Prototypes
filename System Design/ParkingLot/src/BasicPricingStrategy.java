

public class BasicPricingStrategy implements PricingStrategy{

    @Override
    public double calculatFee(VehicleType vehicle, long durationHours) {
        switch(vehicle) {
            case CAR:
                return 50 * durationHours;
            case BIKE:
                return 100 * durationHours;
            case TRUCK:
                return 200 * durationHours;
            default:
                throw new IllegalArgumentException("Not a valid vehicle");
        }
    }
}
