import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private List<ParkingFloor> floors;
    private List<Subscriber> subscribers;

    ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
        this.subscribers = new ArrayList<>();
    }

    public synchronized ParkingTicket bookAvailableSpot(Vehicle vehicle) throws Exception {
        for (ParkingFloor floor : floors) {
            ParkingTicket ticket = floor.bookSpot(vehicle);
            if (ticket != null) {
                notifySubscribers(ticket.getParkingSpot());
                return ticket;
            }
        }
        throw new Exception("No parkign slot is free");
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void exit(ParkingTicket ticket, PaymentStrategy paymentStrategy, PricingFeeStrategy feeStrategy) {
        double fee = feeStrategy
                .calculateFee((int) (Duration.between(ticket.getStartTime(), LocalDateTime.now())).toHours(),
                        ticket.getParkingSpot().getVehicleType());
        paymentStrategy.processPayment(fee);
        ticket.paymentDone();
        ticket.getParkingSpot().freeSpot();
        notifySubscribers(ticket.getParkingSpot());
    }

    public void notifySubscribers(ParkingSpot spot) {
        for (Subscriber subscriber : subscribers) {
            subscriber.updateSpot(spot);
        }
    }
}
