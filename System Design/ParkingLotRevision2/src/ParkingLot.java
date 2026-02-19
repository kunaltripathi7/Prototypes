import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingSpot> slots;

    ParkingLot(int truckSlots, int carSlots, int bikeSlots) {
        this.slots = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < truckSlots; i++)
            slots.add(new ParkingSpot(VehicleType.TRUCK, index++));
        for (int i = 0; i < carSlots; i++)
            slots.add(new ParkingSpot(VehicleType.CAR, index++));
        for (int i = 0; i < bikeSlots; i++)
            slots.add(new ParkingSpot(VehicleType.BIKE, index++));
    }

    public ParkingTicket bookSpot(Vehicle vehicle) {
        for (ParkingSpot spot : slots) {
            if (spot.getVehicleType() == vehicle.getVehicleType() && !spot.isOccupied()) {
                System.out.println("Booked parking spot " + spot.getSpotNumber());
                spot.bookSpot();
                return new ParkingTicket(LocalDateTime.now(), spot, vehicle);
            }
        }
        System.out.println("No parkgin slots are avaialbe for this type of vehicle");
        return null;
    }

    public ParkingTicket exit(ParkingTicket ticket, PaymentStrategy paymentStrategy, PricingFeeStrategy feeStrategy) {
        double fee = feeStrategy
                .calculateFee((int) (Duration.between(ticket.getStartTime(), LocalDateTime.now())).toHours(),
                        ticket.getParkingSpot().getVehicleType());
        paymentStrategy.processPayment(fee);
        ticket.paymentDone(); // method names - struggling
        ticket.getParkingSpot().freeSpot();
        return ticket;
    }
}
