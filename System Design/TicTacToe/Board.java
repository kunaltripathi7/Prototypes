import java.util.Arrays;

public class Board {
    private PieceType[][] board;
    private int movesMade;

    Board(int size) {
        board = new PieceType[size][size];
        movesMade = 0;
        initializeBoard();
    }

    public void initializeBoard() {
        for (PieceType[] row : board)
            Arrays.fill(row, PieceType.N);
        movesMade = 0;
    }

    private boolean checkWin() {
        // rows
        for (PieceType[] row : board) {
            if (row[0] != PieceType.N && Arrays.stream(row).allMatch(element -> element.equals(row[0])))
                return true;
        }

        for (int i = 0; i < board.length; i++) {
            PieceType colParent = board[0][i];
            if (colParent == PieceType.N)
                continue;
            boolean flag = true;
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != colParent) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return true;
        }

        PieceType leftDiagonalParent = board[0][0];
        PieceType rightDiagonalParent = board[board.length - 1][0];
        boolean leftDiagonalFlag = leftDiagonalParent != PieceType.N ? true : false;
        boolean rightDiagonalFlag = rightDiagonalParent != PieceType.N ? true : false;
        for (int i = 1; i < board.length; i++) {
            if (leftDiagonalParent != board[i][i])
                leftDiagonalFlag = false;
            if (rightDiagonalParent != board[board.length - i - 1][i])
                rightDiagonalFlag = false;
        }
        if (leftDiagonalFlag || rightDiagonalFlag)
            return true;
        return false;
    }

    public MoveResult makeMove(PieceType symbol, int row, int col) {
        if (row >= board.length || col >= board.length || row < 0 || col < 0)
            throw new RuntimeException("Invalid value out of bounds");
        if (board[row][col] != PieceType.N)
            throw new RuntimeException("Already occupied");
        board[row][col] = symbol;
        movesMade++;
        if (checkWin())
            return MoveResult.Won;
        if (movesMade == board.length * board.length)
            return MoveResult.Tie;
        return MoveResult.NoChange;
    }

}
