public class HasCardState implements ATMState{
    @Override
    public String getCurrentState() {
        return "HasCardState";
    }

    @Override
    public void advanceState(ATMContext context) {
        if (context.getCard() == null) {
            context.setCurrentState(context.getStateFactory().createtIdleState());
        }
        if (context.getAccount() != null) {

            context.setCurrentState(context.getStateFactory().createSelectOperationState());
        }
    }
}
