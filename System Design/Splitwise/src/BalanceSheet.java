import java.util.HashMap;
import java.util.Map;

public class BalanceSheet implements ExpenseObserver{
    private Map<User, Double> netBalances = new HashMap<>();

    @Override
    public void onExpenseAdded(Expense expense) {
        // recalculate netBalances


        //separate function ->
        for (Expense expense : expenses) {
            Map<User, Double> shares = expense.getShares();
            for (Map.Entry<User, Double> entry : shares.entrySet()) {
                if (entry.getKey().equals(expense.getPayee())) {
                    netBalances.put(entry.getKey(), netBalances.getOrDefault(entry.getKey(), 0.00)+ expense.getAmount() - entry.getValue());
                }
                else {
                    netBalances.put(entry.getKey(), netBalances.getOrDefault(entry.getKey(), 0.00)- entry.getValue());
                }
            }



    }

    @Override
    public void onExpenseRemoved() {

    }


    getSimplifiedSettlements() {

    }


}
