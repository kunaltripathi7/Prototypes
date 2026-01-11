User inserts a card.
User enters Pin.
User Selects transaction.
User withdrawls/checks balance.
User Cancels and takes out the card.
Atm ejects the card/

Clarifying Question:
what exact transaction types should i support? -> Currently I am handling ATM Withdrawl and Check balance
Should i handle multiple pin retries and account locking?
Does user should be allowed to do multiple transactions in one session? -> how i will design the state.
User can cancel at any point in time?

Core Entities:
AccountCore Entities:
Account, Card, ATM INVENTORY, ATM Machine Context

State pattern over here -> idleState, hasCard, selectOperation, Transaction -> How? -> because ATM is going through many states idle and all. and the behaviour of the same operations are changin on the basis of state thastswhy we are gonna use state design pattern.

// it represent a process -> own it the session state
Services -> ATM context -> Behaving as a controller.


, Card, ATM INVENTORY, ATM Machine Context

State pattern over here -> idleState, hasCard, selectOperation, Transaction

Services -> ATM context -> Behaving as a controller.


