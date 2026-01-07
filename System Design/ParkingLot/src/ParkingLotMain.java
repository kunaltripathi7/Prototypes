import java.util.ArrayList;
import java.util.List;

public class ParkingLotMain {

    public static void main(String[] args) {
//        List<ParkingSpot> parkingSpots = new ArrayList<>();
//        ParkingSpot parkingSpot = new CarParkingSpot(1);
//        ParkingSpot parkingSpot2 = new CarParkingSpot(2);
//        ParkingSpot parkingSpot3 = new CarParkingSpot(3);
//        parkingSpots.add(parkingSpot);
//        parkingSpots.add(parkingSpot2);
//        parkingSpots.add(parkingSpot3);
////        ParkingLot parkingLot = new ParkingLot(parkingSpots);
//        PricingStrategy base = new BasicPricingStrategy();
//        Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR, "fdasfdaf", base);
//        parkingLot.parkVehicle(car.getType());
//        PaymentStrategy paymentStrategy = new CreditCardPayment();
//        Payment payment = new Payment(paymentStrategy, car.calculateFee());
//        payment.processPayment();


        ParkingLot parkingLot1 = new ParkingLot.Builder()
                .createNewFloor(20, 29, 29, 1).build();
        PricingStrategy base = new BasicPricingStrategy();
        Vehicle car = VehicleFactory.createVehicle(VehicleType.CAR, "fdasfdaf", base);
        parkingLot1.parkVehicle(car.getType());
        PaymentStrategy paymentStrategy = new CreditCardPayment();
        Payment payment = new Payment(paymentStrategy, car.calculateFee());
        payment.processPayment();
    }
}
