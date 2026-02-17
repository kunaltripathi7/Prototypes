public class SelectOperationState implements ATMState{
    @Override
    public String getCurrentState() {
        return "SelectOperation";
    }

    @Override
    public void advanceState(ATMContext context) {
        if (context.getCard() != null && context.getType() != null) {
            context.setCurrentState(context.getStateFactory().createTransactionState());
        }

    }
}
