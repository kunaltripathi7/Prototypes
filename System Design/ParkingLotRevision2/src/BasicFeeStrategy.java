public class BasicFeeStrategy implements PricingFeeStrategy {

    @Override
    public double calculateFee(int totalHours, VehicleType vehicleType) {
        return totalHours * 5;

        // basis of different vehicles calculate fee
    }

}
