public class Dashboard implements Subscriber {
    private int carSlots, bikeSlots, truckSlots;

    Dashboard(int carSlots, int bikeSlots, int truckSlots) {
        this.bikeSlots = bikeSlots;
        this.carSlots = carSlots;
        this.truckSlots = truckSlots;
    }

    @Override
    public void updateSpot(ParkingSpot spot) {
        int delta = spot.isOccupied() ? -1 : 1;
        switch (spot.getVehicleType()) {
            case CAR:
                carSlots += delta;
                break;
            case BIKE:
                bikeSlots += delta;
                break;
            case TRUCK:
                truckSlots += delta;
                break;
        }
        System.out.println("Dashboard -> Cars: " + carSlots + " Bikes: " + bikeSlots + " Trucks: " + truckSlots);
    }

}
