public class BasicFeeStrategy implements PricingFeeStrategy {

    @Override
    public double calculateFee(int totalHours, VehicleType vehicleType) {
        switch (vehicleType) {
            case BIKE:
                return totalHours * 5;
            case CAR:
                return totalHours * 10;
            case TRUCK:
                return totalHours * 15;
            default:
                return totalHours * 10;
        }
    }

}
