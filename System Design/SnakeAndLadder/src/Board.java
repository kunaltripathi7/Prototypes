public class Board {
    private Cell[][] board;
    private int sideLength;
    private int size;


    public Board(int size) {
        this.size = size;
        this.sideLength = (int) Math.sqrt(size);
        initializeBoard();
    }

    private int getRow(int position) {
        int row = (position-1) /sideLength;
        return sideLength-1-row;
    }

    private int getCol(int position) {
        int row = getRow(position);
        int col = (position-1)%sideLength;
        return (row%2 == 0) ? sideLength-1-col : col;
    }

    private void initializeBoard() {
        this.board = new Cell[sideLength][sideLength];
        int start = 1;
        boolean leftToRight = true;
        for (int i=sideLength-1; i>=0; i--) {
            for (int j =0; j<sideLength; j++) {
                if (leftToRight) {
                    board[i][j] = new Cell(start++, null);// think aobut this.
                }
                else {
                    board[i][sideLength-j-1] = new Cell(start++, null);
                }
            }
            leftToRight = !leftToRight;
        }
    }

    public Cell getCell(int position) {
        int row = getRow(position);
        int col = getCol(position);
        return board[row][col];
    }

    public boolean addObstacle(Obstacle obstacle) {
        Cell source = getCell(obstacle.src);
        Cell dest = getCell(obstacle.dest);
        if (source.hasObstacle() || dest.hasObstacle()) throw new IllegalArgumentException("Not valid");
        source.setObstacle(obstacle);
        return true;
    }

    public int getNewPostion(Player player, int offSet) {
        int currentPosition = player.getPosition();
        if (currentPosition+offSet > size) {
            return currentPosition;
        }
        int newPosition = currentPosition+offSet;
        Cell next = getCell(newPosition);
        if (next.hasObstacle()) {
            return next.getFinalPosition();
        }
        else return newPosition;
    }

}
