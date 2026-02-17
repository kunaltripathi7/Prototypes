import java.util.ArrayList;
import java.util.List;

public class ExpenseService implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<Expense> expenses = new ArrayList<>();


    public void createExpense(int id, int amount,  String location, User payee, List<User> participants, SplitStrategy splitStrategy) {
        Expense expense = new Expense(id, amount, location, payee, participants);
        expenses.add(expense);
        for (Observer observer : observers) observer.onExpenseAdded(expense);
    }


    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);

    }

    @Override
    public void removeObserver(Observer observer) {
            observers.remove(observer);
    }
}
