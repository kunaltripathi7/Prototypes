import java.time.LocalDateTime;

public class ParkingTicket {
    private boolean isPaid;
    private LocalDateTime startTime;
    private ParkingSpot parkingSpot;
    private Vehicle vehicle;

    ParkingTicket(LocalDateTime starTime, ParkingSpot spot, Vehicle vehicle) {
        this.startTime = starTime;
        this.parkingSpot = spot;
        this.vehicle = vehicle;
    }

    // getter setter

    public LocalDateTime getStartTime() {
        return this.startTime;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
    // complete payment method

    public void paymentDone() {
        this.isPaid = true;
    }

    public ParkingSpot getParkingSpot() {
        return parkingSpot;
    }
}
