public class CreditCardPayment implements PaymentStrategy {
    private int creditCardNumber;
    private int pin;

    CreditCardPayment(int creditCardNumber, int pin) {
        this.creditCardNumber = creditCardNumber;
        this.pin = pin;
    }

    @Override
    public void processPayment(double amount) {
        // do something with card and process paymetn
    }

}
