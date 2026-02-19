public class CashPayment implements PaymentStrategy {

    @Override
    public void processPayment(double amount) {
        System.out.println("Successfully paid");
        // otherwise throw exception
    }

}
