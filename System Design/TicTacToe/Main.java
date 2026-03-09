public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Max", PieceType.X, new HumanStrategy());
        Player player2 = new Player("MINI", PieceType.O, new HumanStrategy());
        Game game = new Game(3, player1, player2);
        // to amke move get teh position from strategy then make move in game.
    }
}
