public class TransactionState implements ATMState{
    @Override
    public String getCurrentState() {
        return "TransactionState";
    }

    @Override
    public void advanceState(ATMContext context) {
        if (context.getCard() == null) {
            context.setCurrentState(context.getStateFactory().createtIdleState());
        }
        context.setCurrentState(context.getStateFactory().createHasCardState());

    }
}
