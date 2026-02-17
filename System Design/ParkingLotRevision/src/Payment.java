public class Payment {
    private int paymentId;
    private PaymentStrategy paymentStrategy;
    private double amount;

    public Payment(int paymentId, PaymentStrategy paymentStrategy, double amount) {
        this.paymentId = paymentId;
        this.paymentStrategy = paymentStrategy;
        this.amount = amount;
    }


    public void processPayment() {
        paymentStrategy.processPayment(amount);
    }
}
