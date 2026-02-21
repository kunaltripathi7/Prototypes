import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingFloor {
    List<ParkingSpot> slots;
    private int floorNumber;

    ParkingFloor(int truckSlots, int carSlots, int bikeSlots, int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
        int index = 1;
        for (int i = 0; i < truckSlots; i++)
            slots.add(new ParkingSpot(VehicleType.TRUCK, index++));
        for (int i = 0; i < carSlots; i++)
            slots.add(new ParkingSpot(VehicleType.CAR, index++));
        for (int i = 0; i < bikeSlots; i++)
            slots.add(new ParkingSpot(VehicleType.BIKE, index++));
    }

    // get slots

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

}
