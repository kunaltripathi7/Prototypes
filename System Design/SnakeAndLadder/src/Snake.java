public class Snake extends Obstacle{


    public Snake(int src, int tail) {
        super(src, tail);
    }


    @Override
    ObstacleType getObstacleType() {
        return ObstacleType.Snake;
    }
}
