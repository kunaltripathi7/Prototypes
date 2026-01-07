import java.util.List;

public class IdleState implements VendingMachineState {



    @Override
    public void insertCoin( VendingMachineContext context, Coin c,) {
        if (c == null) throw new NullPointerException("Null Coin");
        context.insertCoin(c);
        context.setState(StateType.HAS_MONEY);
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
