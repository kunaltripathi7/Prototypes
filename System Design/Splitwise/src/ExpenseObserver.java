public interface ExpenseObserver {
    void onExpenseAdded(Expense expense);
    void onExpenseRemoved();
}
