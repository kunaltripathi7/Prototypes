package Models;

public interface IPaymentProvider {
    boolean processPayment(double amount);
}
