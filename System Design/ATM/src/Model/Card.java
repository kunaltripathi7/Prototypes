package Model;

public class Card {
    private final int cardNumber;
    private int pin;

    public Card(int cardNumber, int pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
