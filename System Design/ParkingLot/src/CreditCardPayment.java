public class CreditCardPayment implements PaymentStrategy{

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Credit Card Payment done " + amount);
        return false;
    }

}
