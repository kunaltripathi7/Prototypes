import Enums.TransactionType;
import Model.Account;
import Model.Card;

public class Main {
    public static void main(String[] args) {

        ATMContext atm = new ATMContext();
        atm.createAccount(654321, 100);

        System.out.println("=== Starting ATM Demo ===");

        // Insert card
        atm.insertCard(new Card(123456, 1234, 654321));

        // Enter PIN
        atm.authenticatePin(1234);

        // Select operation
        atm.selectOperation(TransactionType.WITHDRAWL);

        // Perform transaction
        atm.performTransaction(100);

        // Select another operation
        atm.selectOperation(TransactionType.CHECK_BALANCE);

        // Perform balance check
        atm.performTransaction(0);

        // Return card

        System.out.println("=== ATM Demo Completed ===");

    }
}
