import java.time.LocalDateTime;

// shared state -> license plate and eveery vehicle have some common behaviour -> initailizing with a valid state we need a constrcutor
public abstract class Vehicle {
    protected String licensePlate; // always in vehicle class use protected
    protected VehicleType type;
    protected PricingStrategy pricingStrategy;
    protected long startTime;

    public Vehicle(String licensePlate, VehicleType type, PricingStrategy pricingStrategy) {
        this.licensePlate = licensePlate;
        this.type = type;
        this.pricingStrategy = pricingStrategy;
        this.startTime = System.currentTimeMillis();
    }

    public VehicleType  getType() {
        return type;
    }

    public double calculateFee() {
        long currentTime = System.currentTimeMillis();
        long totalDuration = (long) Math.ceil((double) (currentTime - startTime) / 1000*60*60*60);
        return pricingStrategy.calculatFee(type, totalDuration);
    }

}

