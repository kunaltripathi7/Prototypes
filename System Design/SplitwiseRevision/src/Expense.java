import java.util.List;
import java.util.Map;
import java.util.Observer;

public class Expense {
    private int expenseId;
    private User payee;
    private Map<User, Double> shares;
    private String location;
    private List<User> participants;
    double amount;

    public Expense(int expenseId, int amount, String location, User payee, List<User> participants) {
        this.expenseId = expenseId;
        this.location = location;
        this.payee = payee;
        this.participants = participants;
        this.amount = amount;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public Map<User, Double> getShares() {
        return shares;
    }

    public void setShares(Map<User, Double> shares) {
        this.shares = shares;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
