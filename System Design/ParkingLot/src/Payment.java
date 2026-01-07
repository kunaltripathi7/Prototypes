public class Payment {

    private final double amount;
    private final PaymentStrategy paymentStrategy;

    public Payment(PaymentStrategy paymentStrategy, double amount) {
        this.paymentStrategy = paymentStrategy;
        this.amount = amount;
    }

    public boolean processPayment() {
        return paymentStrategy.processPayment(amount);
    }
}
