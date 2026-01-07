public class Ladder extends Obstacle{

    public Ladder(int src, int dest) {
        super(dest, src); // we are putting obstacle at the source.
    }

    @Override
    ObstacleType getObstacleType() {
        return null;
    }
}
