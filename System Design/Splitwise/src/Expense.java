import java.util.Date;
import java.util.List;
import java.util.Map;

//@Getter
//@Setter
public class Expense {
    private int id;
    private User payee;
    private double amount;
    private Date date;
    private String description;
    private List<User> participants;
    private Map<User, Double> shares;

//    public Expense(int id, User payee, double amount, D) {
//     constructor.
//    }

}
