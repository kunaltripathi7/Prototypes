import java.util.Arrays;

public class Board {
    private PieceType[][] board;

    Board() {
        board = new PieceType[3][3];
        initializeBoard();
    }

    public void initializeBoard() {
        for (PieceType[] row : board)
            Arrays.fill(row, PieceType.N);
    }

    private boolean checkWin() {
        for ()
    }
}
