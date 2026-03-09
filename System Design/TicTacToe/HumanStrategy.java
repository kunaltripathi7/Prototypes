import java.util.Scanner;

public class HumanStrategy implements MoveStrategy {

    @Override
    public Position makeMove() {
        Scanner sc = new Scanner(System.in);
        int row = sc.nextInt();
        int col = sc.nextInt();
        return new Position(row, col);
    }

}
