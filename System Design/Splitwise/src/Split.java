import java.util.List;
import java.util.Map;

public interface Split {
    Map<User, Double> calculate(double amount, List<User> users, Map<String, Object> splitDetails);
}
