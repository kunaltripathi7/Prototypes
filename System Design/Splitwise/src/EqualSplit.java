import java.util.HashMap;
import java.util.List;
import java.util.Map;

class EqualSplit implements Split {
    public Map<User, Double> calculate(double amount, List<User> users, Map<String, Object> splitDetails) {

        // Your logic here
        Map<User, Double> map = new HashMap<>();
        for (User u : users) {
            map.put(u, amount/users.size());
        }
        return map;
    }
}

//same talk about percentage split && amountSplit


//Map<User, Double> percentages = (Map<User, Double>) splitDetails.get("percentages");
