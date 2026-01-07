import java.util.Map;

public interface SplitStrategy {
    Map<User, Double> calculateSplit(List<Expense> expenses,  ,Map<String, Object> splitDetails);
}
