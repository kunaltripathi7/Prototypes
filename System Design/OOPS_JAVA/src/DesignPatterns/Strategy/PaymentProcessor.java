package DesignPatterns.Strategy;

public class PaymentProcessor {


//    public void processPayment(String type) {

//        if (type.equals("PayPal")) {
//            //process paypal payment method
//        } else if (type.equals("CreditCard")) {
//            //process credit card payment
//        } else if (type.equals("DebitCard")) {
//            //process debit card
//            DebitCardPayment debitCardPayment = new DebitCardPayment();
//            debitCardPayment.processPayment();
//        } else {
//            // do more
//        }
//    }

    private PaymentStrategy paymentStrategy;
    public PaymentProcessor(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    public void processPayment(PaymentStrategy paymentStrategy) {
        paymentStrategy.processPayment();
    }

    //to eliminate this if else zoom out and choose the super class/interface so that it supports all subclasses of that.

}



// problem -> Open/closed principle not followed -> modifying the code -> not scalable -> long file if we kept on adding things to same file -> Solution -> Factory design pattern


// Then also doing this -> we are violating open closed principle -> just scale this up what if you have 1k payment systems -> if else is bad -> Identify -> Different strategies to solve one thing -> Different choices on runtime -> Strategy design pattern


