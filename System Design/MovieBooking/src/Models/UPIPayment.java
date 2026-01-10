package Models;

public class UPIPayment implements IPaymentProvider{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("processed payment");
        return true;
    }
}
