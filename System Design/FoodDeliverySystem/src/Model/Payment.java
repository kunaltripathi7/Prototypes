package Model;

import Enums.PaymentStatus;
import Interfaces.PaymentStrategy;

public class Payment {
    private PaymentStatus paymentStatus;
    private final double amount;

    public Payment(double amount) {
        this.paymentStatus = PaymentStatus.CREATED;
        this.amount = amount;
    }


    // why payment strategy if here payByUpi and all so -> cluttered. -> open/closed principle - violated -> zoom out -> make abstraction.

    public void processPayment(PaymentStrategy strategy) {
        boolean isSucess = strategy.processPayment(amount);
        this.paymentStatus = isSucess ? PaymentStatus.COMPLETED : PaymentStatus.FAILED;
    }

    public boolean isSuccessful() {
        return paymentStatus == PaymentStatus.COMPLETED;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public double getAmount() {
        return amount;
    }
}