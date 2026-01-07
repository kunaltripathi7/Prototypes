import java.util.ArrayList;
import java.util.List;

public class CashPaymentStrategy implements PaymentStrategy {
    private final List<Coin> coins = new ArrayList<>();

    @Override
    public void accept(Coin coin) {
        coins.add(coin);
    }

    @Override
    public boolean hasSufficientBalance(int amount) {
        return coins.stream().mapToInt(c -> c.value).sum() >= amount;
    }

    @Override
    public List<Coin> refund() {
        List<Coin> refund = new ArrayList<>(coins);
        coins.clear();
        return refund;
    }

    @Override
    public void reset() {
        coins.clear();
    }
}
