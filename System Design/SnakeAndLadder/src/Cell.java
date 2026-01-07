public class Cell {
    private Obstacle obstacle;
    private final int position;


    public Cell(int position, Obstacle obstacle) {
        this.position = position;
        this.obstacle = obstacle;
    }

    public boolean hasObstacle() {
        return obstacle != null;
    }

    public int getFinalPosition() {
        return hasObstacle() ? obstacle.movePlayer() : position;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

}
