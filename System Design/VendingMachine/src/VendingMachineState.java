import java.util.List;

public interface VendingMachineState {
    void insertCoin(VendingMachineContext context, Coin c);
    Item dispense(VendingMachineContext context);
    List<Coin> refund(VendingMachineContext context);
    int selectItem(VendingMachineContext context);
}
