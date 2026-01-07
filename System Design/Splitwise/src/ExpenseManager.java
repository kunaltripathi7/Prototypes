import java.util.*;

public class ExpenseManager implements ExpenseSubject{
    //what the expenseManager -> Subject should have -> list of observers and the thing on which it will change
    List<ExpenseObserver> observers = new ArrayList<>();
    List<Expense> expenses = new ArrayList<>();

    private
    private List<Expense> expenses;
}


@Override
        public void addObserver() {

}


addExpense() {

}

public int minTransactions(List<Expense> expenses) {
    List<Double> balances = new ArrayList<>();

    return new minTransactions(0, balances);
}


int minTransactions(int start, List<Double> balances) {
    while (start < balances.size() && balances.get(start) == 0) {
        start++;
    }

    int minCount = Integer.MAX_VALUE;
    if (start == balances.size()) return 0;
    double currentBalance = balances.get(start);
    for (int i = start+1; i<balances.size(); i++) {
        double original = balances.get(i);
        if (original * currentBalance < 0) {
            balances.set(i, original+currentBalance);
            minCount = Math.min(minCount, 1 + minTransactions(start+1, balances));
            balances.set(i, original);
        }
    }
    return minCount == Integer.MAX_VALUE ? 0 : minCount;

}




public Map<User, Double> getNetBalances(List<Expense> expenses) {
    Map<User, Double> netBalances = new HashMap<>();

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
    return netBalances;
}





    //list of expense from a group.

    public List<Transaction> simplifyDebts(List<Expense> expenses) {


        List<User> creditors = new ArrayList<>();
        List<User> debtors = new ArrayList<>();

        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
            if (entry.getValue() > 0) creditors.add(entry.getKey());
            else debtors.add(entry.getKey());
        }

        List<Transaction> result = new ArrayList<>();
// check if the sum is same

        PriorityQueue<User> maxCreditor = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(b), netBalances.get(a)));
        PriorityQueue<User> maxDebtor = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(a), netBalances.get(b)));

        maxCreditor.addAll(creditors);
        maxDebtor.addAll(debtors);


//        while (!maxCreditor.isEmpty() && !maxDebtor.isEmpty()) {
//            User from = maxDebtor.poll();
//            User to = maxCreditor.poll();
//            double amountPayable = netBalances.get(from);
//            double amountReceivable = netBalances.get(to);
//            Transaction transaction = new Transaction(from, to, Math.min(Math.abs(amountPayable), amountReceivable) );
////            if (amountPayable == amountReceivable) {
////                netBalances.remove(from);
////                netBalances.remove(to);
////            }
////            else if (amountPayable > amountReceivable) {
////                netBalances.put(from, amountPayable+amountReceivable);
////                maxDebtor.add(from);
////            }
////            else {
////                netBalances.put(to, amountReceivable-amountPayable);
////                maxDebtor.add(to);
////            }
//
//            result.add(transaction);
//        }
//        return result;
//    }

        while (!maxDebtor.isEmpty() && !maxCreditor.isEmpty()) {
            User debtor = maxDebtor.poll();
            User creditor = maxCreditor.poll();

            double debtAmount = Math.abs(netBalances.get(debtor));
            double creditAmount = netBalances.get(creditor);
            double transferAmount = Math.min(debtAmount, creditAmount);

            result.add(new Transaction(debtor, creditor, transferAmount));

            // Update balances
            netBalances.put(debtor, netBalances.get(debtor) + transferAmount);  // -100 + 50 = -50
            netBalances.put(creditor, netBalances.get(creditor) - transferAmount); // 200 - 50 = 150

            // Re-add if not settled
            if (Math.abs(netBalances.get(debtor)) > 0.001) {
                maxDebtor.add(debtor);
            }
            if (netBalances.get(creditor) > 0.001) {
                maxCreditor.add(creditor);
            }
        }
    }



