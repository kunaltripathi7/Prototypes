abstract class Obstacle {
    protected int src;
    protected int dest;

    public Obstacle(int src, int dest) {
        this.src = src;
        this.dest = dest;
    }

    public int movePlayer() {
        return dest;
    }

    abstract ObstacleType getObstacleType();
}
