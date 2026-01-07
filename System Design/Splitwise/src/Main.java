import java.util.List;

public class Main {
    public static void main(String[] args) {
            ExpenseManager manager = new ExpenseManager();
            BalanceSheet balanceSheet = new BalanceSheet();

            manager.addObserver(balanceSheet);


        List<Transaction> settlements = balanceSheet.getSimplifiedSettlements();



        // now every manager.addExpense(expense) automatically updates balances


    }
}
