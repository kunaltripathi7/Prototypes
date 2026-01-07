import java.util.List;

//public class VendingMachineContext {
//    private Inventory inventory;
//    private List<Coin> coins;
//    private int selectedItemCode;
//    private VendingMachineState state;
//
//    public VendingMachineContext(Inventory inventory) {
//        this.inventory = inventory;
//    }
//
//    public VendingMachineState getState() {
//        return state;
//    }
//
//    public void setState(VendingMachineState state) {
//        this.state = state;
//    }
//
//
//    public void transitionTo(StateType stateType) {
//        // logic for moving to next state
//        switch (stateType) {
//            case HAS_MONEY:
//                //conditional logic to check fi
//                this.state = new IdleState();
//                break;
//            case IDLE:
//                    this.state = new HasMoneyState();
//        }
//    }
//
//    public void insertCoin(Coin coin) {
//        //hey clicked // delegates the action to the current state
//        this.state.insertCoin(coin, this);
//
//    }
//
//    public void selectItem(int codeNumber) {
//
//    }
//
//    public void cancel() {
//
//    }
//
//    public void dispense() {
//
//    }
//
//    public int getTotalCoins() {
//        return 0; // logic for adding
//    }
//
//    public void clearCoins() {
//
//    }
//
//    public List<Coin> refund() {
//        return null; // coin refund logic. all the coins
//    }
//
//}

// old vending machine context

public class VendingMachineContext {

    private VendingMachineState state;
    private final InventoryManager inventory;
    private final PaymentStrategy payment;
    private Item selectedItem;

    public VendingMachineContext(InventoryManager inventory, PaymentStrategy payment) {
        this.inventory = inventory;
        this.payment = payment;
        this.state = new IdleState();
    }

    public void insertCoin(Coin coin) {
        state.insertCoin(this, coin);
    }

    public void selectItem(int code) {
        state.selectItem(this, code);
    }

    public Item dispense() {
        return state.dispense(this);
    }

    public List<Coin> refund() {
        return state.refund(this);
    }

    void transitionTo(StateType type) {
        switch (type) {
            case IDLE -> state = new IdleState();
            case HAS_MONEY -> state = new HasMoneyState();
            case DISPENSING -> state = new DispensingState();
        }
    }

    // getters / setters
    InventoryManager getInventory() { return inventory; }
    PaymentStrategy getPayment() { return payment; }
    Item getSelectedItem() { return selectedItem; }
    void setSelectedItem(Item item) { this.selectedItem = item; }
    void clearSelectedItem() { this.selectedItem = null; }
}
