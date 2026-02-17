import Enums.TransactionType;
import Model.ATMInventory;
import Model.Account;
import Model.Card;

import java.util.HashMap;
import java.util.Map;


//ATMContext exposes exactly the actions a user can perform on an ATM.
public class ATMContext {
    private ATMState currentState;
    private Card card;
    private Account account;
    private ATMInventory atmInventory;
    private TransactionType type;
    private ATMStateFactory stateFactory;
    private Map<Integer, Account> accountMap;

    public ATMContext() {
        this.atmInventory = new ATMInventory();
        this.stateFactory = ATMStateFactory.getInstance();
        this.currentState = stateFactory.createtIdleState();
        this.accountMap = new HashMap<>();
    }

    public void createAccount(int accountNumber, int balance) {
        Account account1 = new Account(accountNumber, balance);
        this.account = account1;
        accountMap.put(accountNumber, account1);
    }

    public ATMStateFactory getStateFactory() {
        return stateFactory;
    }

    public void insertCard(Card card) {
        if (currentState instanceof IdleState) {
            this.card = card;
            advanceState();
        } else System.out.println("Not in idel states");
    }

    public void authenticatePin(int pin ) {
        if (currentState instanceof HasCardState) {
            if (pin == this.card.getPin()) {
                advanceState();
            }
            else System.out.println("WRong pin Try again");
        }
        else System.out.println("not allowed");
    }

    public void selectOperation(TransactionType type) {
        if (currentState instanceof SelectOperationState) {
            if (type instanceof TransactionType) {
                this.type = type;
                advanceState();
            }
            else System.out.println("Invalid transaction");
        }
        else {
            System.out.println(currentState.getCurrentState());
            System.out.println("NOt in the right state");
        }
    }

    public void performTransaction(int amount) {
        if (currentState instanceof TransactionState) {
            if (type == TransactionType.WITHDRAWL) {
                if (this.account.getBalance() < amount) System.out.println("You can't");
                atmInventory.processWithdrawl(amount);
            } else if(type == TransactionType.CHECK_BALANCE) {
                account.getBalance();
            }
            advanceState();
        } else System.out.println("can;t perfron operations in this state.");
    }

    public void resetAtm() {
        this.account = null;
        this.card = null;
        this.type = null;
        this.currentState = stateFactory.createtIdleState();
    }


    public void advanceState() {
        currentState.advanceState(this);
    }

    public ATMState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ATMState currentState) {
        this.currentState = currentState;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ATMInventory getAtmInventory() {
        return atmInventory;
    }

    public void setAtmInventory(ATMInventory atmInventory) {
        this.atmInventory = atmInventory;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }
}
