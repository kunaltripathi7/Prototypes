import java.util.Queue;

public class Game {
    private final Queue<Player> players;
    private final Dice dice;
    private final int noOfLadders;
    private final int noOfSnakes;

    public Game(int size, int noOfLadders, int noOfSnakes, int noOfDice, Queue<Player> players, Dice dice, int noOfLadders1, int noOfSnakes1) {
        this.players = players;
        this.dice = dice;
        this.noOfLadders = noOfLadders1;
        this.noOfSnakes = noOfSnakes1;
        this.size = size;

    }

    public void initObstacles() {
        //fill snakes
        //fill ladders
    }

    public void startGame() {
        while (loop) {
            push the player in the queue
                    roll the dice
                    make the move.
        }
    }
}
