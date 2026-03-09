public class Player {
    private String name;
    private final PieceType symbol;
    private MoveStrategy strategy;

    Player(String name, PieceType symbol, MoveStrategy strategy) {
        this.name = name;
        this.symbol = symbol;
        this.strategy = strategy;
    }

    public PieceType getSymbol() {
        return this.symbol;
    }

    public String getName() {
        return this.name;
    }

    public MoveStrategy getStrategy() {
        return this.strategy;
    }
}
