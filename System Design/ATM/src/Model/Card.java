package Model;

public class Card {
    private final int cardNumber;
    private int pin;
    private int accountNumber;

    public Card(int cardNumber, int pin, int accountNumber) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
