import java.util.Random;

public class Game {
    private Board board;
    private Player currPlayerTurn;
    private Player player1;
    private Player player2;
    private boolean gameOver = false;
    Random random;

    Game(int size, Player player1, Player player2) {
        board = new Board(size);
        this.player1 = player1;
        this.player2 = player2;
        random = new Random();
        currPlayerTurn = random.nextBoolean() ? this.player1 : this.player2;
    }

    public void resetGame() {
        board.initializeBoard();
        gameOver = false;
        random = new Random();
        currPlayerTurn = random.nextBoolean() ? this.player1 : this.player2;
    }

    public void playMove(int row, int col) {
        if (gameOver)
            return;
        MoveResult result = board.makeMove(currPlayerTurn.getSymbol(), row, col);
        if (result == MoveResult.Tie) {
            System.out.println("Match Tied");
            gameOver = true;
        } else if (result == MoveResult.Won) {
            System.out.println(
                    currPlayerTurn.getName() + "Won");
            gameOver = true;
        }
        currPlayerTurn = currPlayerTurn == player1 ? player2 : player1;
    }
}
