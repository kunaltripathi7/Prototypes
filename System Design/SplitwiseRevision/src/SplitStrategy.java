import java.util.List;
import java.util.Map;

public interface SplitStrategy {
    Map<User, Double> calculateSplit (double amount, List<User> users, Map<String, Object> splitDetails);
}
