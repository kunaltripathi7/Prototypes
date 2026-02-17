import java.util.*;

public class BalanceSheet implements Observer{
    private Map<UserPair, Double> balances = new HashMap<>();

    @Override
    public void onExpenseAdded(Expense expense) {
        updateBalances(expense);
    }

    public void updateBalances(Expense expense) {
        User payer = expense.getPayee();
        Map<User, Double> shares = expense.getShares();
        for (Map.Entry<User, Double> entry : shares.entrySet()) {
            User participant = entry.getKey();
            double amount = entry.getValue();
            if (!participant.equals(payer)) {
                UserPair userPair = new UserPair(participant, payer);
                double currentBalance=  balances.getOrDefault(userPair, 0.00);
                balances.put(userPair, currentBalance+amount);
            }
        }
    }

//    calculate total balance for a user -> if they owe money + amount otherwise minus from the list.

    public List<Transaction> getSimplifiedSettlements() {
        Map<User, Double> netBalances = new HashMap<>();

        // calculate netBalances
        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
            double amount = entry.getValue();
            User participant = entry.getKey().getUser1();
            User payer = entry.getKey().getUser2();
            netBalances.put(participant, netBalances.getOrDefault(participant, 0.00)-amount);
            netBalances.put(payer, netBalances.getOrDefault(payer, 0.00)+amount);
        }
        PriorityQueue<User> creditors  = new PriorityQueue<>((a,b) -> Double.compare(netBalances.get(b),netBalances.get(a)));
        PriorityQueue<User> debtors = new PriorityQueue<>((a, b) -> Double.compare(netBalances.get(a), netBalances.get(b)));
        for (Map.Entry<User, Double> entry : netBalances.entrySet()){
            double amount = entry.getValue();
            User user = entry.getKey();
            if (amount > 0) {
                creditors.add(user);
            }else debtors.add(user);
        }
        List<Transaction> transactions = new ArrayList<>();

        while (!creditors.isEmpty() && !debtors.isEmpty()) {
            User maxCreditor = creditors.poll();
            User maxDebtor = debtors.poll();
            double creditAmount = netBalances.get(maxCreditor);
            double debitAmount  = netBalances.get(maxDebtor);
            double transferAmount = Math.min(creditAmount, Math.abs(debitAmount));
            netBalances.put(maxDebtor, netBalances.get(maxDebtor)+transferAmount);
            netBalances.put(maxCreditor, netBalances.get(maxCreditor)-transferAmount);
            if (Math.abs(netBalances.get(maxDebtor)) > 0.001) debtors.add(maxDebtor);
            if (Math.abs(netBalances.get(maxDebtor)) > 0.001) debtors.add(maxCreditor);
        }
        return transactions;
    }


    int minimumNumberOfSettlements(int currentIndex, List<Double> creditList, int n) {
        while (currentIndex < n && creditList.get(currentIndex) == 0) currentIndex++;
        if (currentIndex == n) return 0;
        int cost = Integer.MAX_VALUE;
        for (int i = currentIndex+1; i<n; i++) {
            if (creditList.get(currentIndex) * creditList.get(i) < 0) {
                //pick
                creditList.set(i, creditList.get(i)+creditList.get(currentIndex));
                cost = Math.min(cost, 1 + minimumNumberOfSettlements(currentIndex+1,creditList, n));
                creditList.set(i, creditList.get(i)-creditList.get(currentIndex));
            }
        }
        return cost;

    }

    @Override
    public void onExpenseRemoved(Expense expense) {
        updateBalances(expense);
    }
}
