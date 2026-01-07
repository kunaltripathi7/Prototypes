import java.util.Random;

public class Dice {
    private int numberOfDices;
    private final Random random = new Random();

    public Dice(int number) {
        this.numberOfDices = number;
    }

    public int getDiceResult() {
        int sum = 0;
        for (int i=0; i<numberOfDices; i++) {
            int currentDiceResult = random.nextInt(6)+1;
            sum += currentDiceResult;
        }

        return sum;
    }
}
