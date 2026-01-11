import Interfaces.PaymentStrategy;

public class UPIPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean processPayment(double amount) {
        System.out.println("payment completed");
        return true;
    }
}
