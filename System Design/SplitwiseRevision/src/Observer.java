public interface Observer {
    void onExpenseAdded(Expense expense);
    void onExpenseRemoved(Expense expense);
}
