public interface PricingStrategy {
   double calculatFee(VehicleType vehicle, long durationHours);
}
