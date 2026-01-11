package Model;

public class Account {
    private final int accountNumber;
    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
