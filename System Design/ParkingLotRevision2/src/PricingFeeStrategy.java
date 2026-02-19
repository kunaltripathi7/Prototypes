public interface PricingFeeStrategy {
    public double calculateFee(int totalHours, VehicleType vehicleType);
}
