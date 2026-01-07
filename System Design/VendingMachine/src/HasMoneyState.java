import java.util.List;

public class HasMoneyState implements VendingMachineState {

    @Override
    public void insertCoin(VendingMachineContext context, Coin c  ) {
        if (c == null) throw new NullPointerException("Null Coin");
        context.insertCoin(c);
//        context.setState(new SelectionState());
    }

    @Override
    public Item dispense(VendingMachineContext context) {
        return null;
    }

    @Override
    public List<Coin> refund(VendingMachineContext context) {
        return List.of();
    }

    @Override
    public int selectItem(VendingMachineContext context) {
        return 0;
    }

}
