public interface ATMState {
    String getCurrentState();
    void advanceState(ATMContext context);
}
