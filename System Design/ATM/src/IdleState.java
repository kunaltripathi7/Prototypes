public class IdleState implements ATMState{
    @Override
    public String getCurrentState() {
        return "IdleState";
    }

    @Override
    public void advanceState(ATMContext context) {
        if (context.getCard() != null) {
            context.setCurrentState(new HasCardState());
        }
    }
}
