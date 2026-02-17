import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EqualSplit implements SplitStrategy{
    @Override
    public Map<User, Double> calculateSplit(double amount, List<User> users, Map<String, Object> splitDetails) {
        Map<User, Double> shares = new HashMap<>();
        for (User user : users) {
            shares.put(user, amount/users.size());
        }
        return shares;
    }
}
