public class CreditCardPayment implements PaymentStrategy{
    public int cardNumber;

    public CreditCardPayment(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        if (amount < 0) throw new IllegalArgumentException("Wrong data");
        System.out.println("Payment completed");
    }


}
