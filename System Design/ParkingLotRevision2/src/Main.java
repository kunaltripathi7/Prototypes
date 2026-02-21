public class Main {

    public static void main(String[] args) {
        ParkingLot pl = new ParkingLot(2, 1, 2);

        Vehicle car = new Vehicle("KA-01-1234", VehicleType.CAR);
        Vehicle bike = new Vehicle("KA-02-5678", VehicleType.BIKE);

        ParkingTicket carTicket = pl.bookSpot(car);
        ParkingTicket bikeTicket = pl.bookSpot(bike);

        pl.exit(carTicket, new CashPayment(), new BasicFeeStrategy());
        pl.exit(bikeTicket, new CreditCardPayment(1234, 5678), new BasicFeeStrategy());
    }

}
