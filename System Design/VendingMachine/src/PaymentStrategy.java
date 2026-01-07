import java.util.List;

public interface PaymentStrategy {
        void accept(Coin coin);
        boolean hasSufficientBalance(int amount);
        List<Coin> refund();
        void reset();

}
