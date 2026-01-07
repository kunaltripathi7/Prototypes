///**
// - Represents a user in the system.
// */
//public class User {
//    private String id;       // Unique identifier for the user
//    private String name;     // Name of the user
//    private String email;    // Email address of the user
//
//    // Constructor to initialize User attributes
//    public User(String id, String name, String email) {
//        this.id = id;
//        this.name = name;
//        this.email = email;
//    }
//
//    // Getters for the user's attributes
//    public String getId() { return id; }
//    public String getName() { return name; }
//    public String getEmail() { return email; }
//
//    // Override equals() to compare users by ID
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof User)) return false;
//        User user = (User) o;
//        return id.equals(user.id);
//    }
//
//    // Override hashCode() to generate hash based on user ID
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }
//}
//
//public class Expense {
//    private String id;                     // id of the expense
//    private String description;           // Description of the expense
//    private double amount;                // Total amount of the expense
//    private User payer;                   // User who paid the expense
//    private List<User> participants;      // List of users sharing the expense
//    private Map<User, Double> shares;     // Split amounts owed by each participant
//
//    // Constructor to initialize Expense attributes
//    public Expense(String id, String description, double amount, User payer, List<User> participants, Map<User, Double> shares) {
//        this.id = id;
//        this.description = description;
//        this.amount = amount;
//        this.payer = payer;
//        this.participants = participants;
//        this.shares = shares;
//    }
//
//    // Getters for the expense attributes
//    public String getId() { return id; }
//    public String getDescription() { return description; }
//    public double getAmount() { return amount; }
//    public User getPayer() { return payer; }
//    public List<User> getParticipants() { return participants; }
//    public Map<User, Double> getShares() { return shares; }
//}
//
///**
// - Represents a financial transaction between two users.
// */
//public class Transaction {
//    private User from;        // User who owes the money
//    private User to;          // User who is owed the money
//    private double amount;    // Transaction amount
//
//    // Constructor to initialize Transaction attributes
//    public Transaction(User from, User to, double amount) {
//        this.from = from;
//        this.to = to;
//        this.amount = amount;
//    }
//
//    // Getters for the transaction attributes
//    public User getFrom() { return from; }
//    public User getTo() { return to; }
//    public double getAmount() { return amount; }
//}
//
//
//public class UserPair {
//    private User user1;
//    private User user2;
//    public UserPair(User user1, User user2){
//        this.user1 = user1;
//        this.user2 = user2;
//    }
//    public User getUser1(){
//        return user1;
//    }
//    public User getUser2(){
//        return user2;
//    }
//}
//
//// Interface defining the behavior for all types of splits
//interface Split {
//    /**
//     - Calculates the split for the given amount among participants based on specific split details.
//     -
//     - @param amount        The total amount to split.
//     - @param participants  The list of users participating in the split.
//     - @param splitDetails  Additional details required for the specific split type.
//     - @return A map where the key is the User and the value is the amount they owe.
//     */
//    Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails);
//}
//
//
//// Implementation of the Split interface for equal split
//public class EqualSplit implements Split {
//    @Override
//    public Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails) {
//        double amountPerPerson = amount / participants.size(); // Divide the amount equally among all participants
//        Map<User, Double> splits = new HashMap<>(); // Map to hold the calculated split
//        for (User user : participants) {
//            splits.put(user, amountPerPerson); // Assign each participant the equal amount
//        }
//        return splits;
//    }
//}
//
//
//public class PercentageSplit implements Split {
//    @Override
//    public Map<User, Double> calculateSplit(double amount, List<User> participants, Map<String, Object> splitDetails) {
//        // Retrieve the percentage allocation for each participant from the split details
//        Map<User, Double> percentages = (Map<User, Double>) splitDetails.get("percentages");
//        Map<User, Double> splits = new HashMap<>(); // Map to hold the calculated split
//
//
//        for (User user : participants) {
//            double percentage = percentages.getOrDefault(user, 0.0); // Get the percentage for the user
//            splits.put(user, amount * percentage / 100.0); // Calculate the share based on the percentage
//        }
//        return splits;
//    }
//}
//
//// Factory class for creating instances of different Split types
//public class SplitFactory {
//    /**
//     - Factory method to create a Split instance based on the specified split type.
//     - 	     - @param splitType The type of split to create ("EQUAL", "PERCENTAGE", "EXACT").
//     - @return An instance of the corresponding Split implementation.
//     - @throws IllegalArgumentException if the split type is unknown.
//     */
//    public static Split createSplit(String splitType) {
//        switch (splitType) {
//            case "EQUAL":
//                return new EqualSplit(); // Return an EqualSplit instance
//            case "PERCENTAGE":
//                return new PercentageSplit(); // Return a PercentageSplit instance
//            default:
//                // Throw an exception if the split type is invalid
//                throw new IllegalArgumentException("Unknown split type: " + splitType);
//        }
//    }
//}
//
//// Interface for observers that need to be notified of expense updates.
//interface ExpenseObserver {
//    // Called when a new expense is added to the system.
//    void onExpenseAdded(Expense expense);
//
//    // Called when an expense is updated in the system.
//    void onExpenseUpdated(Expense expense);
//}
//
//
//// Interface for objects that can be observed for expense updates.
//interface ExpenseSubject {
//    // Adds an observer to the notification list.
//    void addObserver(ExpenseObserver observer);
//
//    // Removes an observer from the notification list.
//    void removeObserver(ExpenseObserver observer);
//
//    // Notifies all observers about a new expense.
//    void notifyExpenseAdded(Expense expense);
//
//    // Notifies all observers about an updated expense.
//    void notifyExpenseUpdated(Expense expense);
//}
//
//
//// Concrete implementation of ExpenseSubject that manages expense notifications.
//public class ExpenseManager implements ExpenseSubject {
//    private List<ExpenseObserver> observers = new ArrayList<>();
//    private List<Expense> expenses = new ArrayList<>();
//
//    @Override
//    public void addObserver(ExpenseObserver observer) {
//        observers.add(observer);
//    }
//
//    @Override
//    public void removeObserver(ExpenseObserver observer) {
//        observers.remove(observer);
//    }
//
//    @Override
//    public void notifyExpenseAdded(Expense expense) {
//        for (ExpenseObserver observer : observers) observer.onExpenseAdded(expense);
//    }
//
//    @Override
//    public void notifyExpenseUpdated(Expense expense) {
//        for (ExpenseObserver observer : observers) observer.onExpenseUpdated(expense);
//    }
//
//    // Adds a new expense to the system and notifies observers.
//    public void addExpense(Expense expense) {
//        expenses.add(expense);
//        notifyExpenseAdded(expense);
//    }
//
//    // Updates an existing expense and notifies observers.
//    public void updateExpense(Expense expense) {
//        // Find and replace the expense with the same ID in the list
//        for (int i = 0; i < expenses.size(); i++) {
//            if (expenses.get(i).getId().equals(expense.getId())) { // Check if the IDs match
//                expenses.set(i, expense); // Replace the old expense with the updated one
//                notifyExpenseUpdated(expense); // Notify all observers about the update
//                return; // Exit the method after updating
//            }
//        }
//        // Throw an exception if the expense with the given ID is not found
//        throw new IllegalArgumentException("Expense with ID " + expense.getId() + " not found.");
//    }
//
//    // Retrieves all expenses in the system.
//    public List<Expense> getAllExpenses() {
//        return new ArrayList<>(expenses);
//    }
//}
//
//
//**
//        - Balance sheet that observes expenses and calculates balances.
//        */
//public class BalanceSheet implements ExpenseObserver {
//    // Stores the net balance between pairs of users
//    private Map<UserPair, Double> balances = new HashMap<>();
//
//    @Override
//    public void onExpenseAdded(Expense expense) {
//        // Update balances when a new expense is added
//        updateBalances(expense);
//    }
//
//    @Override
//    public void onExpenseUpdated(Expense expense) {
//        // To simplify logic, just update balances without reversing the previous state
//        updateBalances(expense);
//    }
//
//    /**
//     - Updates the balances based on a new or updated expense.
//     - Each participant's share is added to their balance with the payer.
//     -    - @param expense The expense to process.
//     */
//    private void updateBalances(Expense expense) {
//        User payer = expense.getPayer(); // User who paid for the expense
//        Map<User, Double> shares = expense.getShares(); // Participants and their shares
//        for (Map.Entry<User, Double> entry : shares.entrySet()) {
//            User participant = entry.getKey(); // A participant in the expense
//            Double amount = entry.getValue(); // The amount owed by the participant
//            if (!participant.equals(payer)) {
//                // Create a unique pair for the payer and participant
//                UserPair userPair = new UserPair(participant, payer);
//                // Update the balance (add the amount owed by the participant)
//                Double currentBalance = balances.getOrDefault(userPair, 0.0);
//                balances.put(userPair, currentBalance + amount);
//            }
//        }
//    }
//
//    /**
//     - Gets the net balance between two users.
//     -    - @param user1 First user.
//     - @param user2 Second user.
//     - @return The amount user1 owes user2 (negative if user2 owes user1).
//     */
//    public double getBalance(User user1, User user2) {
//        // Represent the balance both ways (user1 -> user2 and user2 -> user1)
//        UserPair pair1 = new UserPair(user1, user2);
//        UserPair pair2 = new UserPair(user2, user1);
//        // Retrieve balances in both directions and calculate the net
//        double balance1 = balances.getOrDefault(pair1, 0.0);
//        double balance2 = balances.getOrDefault(pair2, 0.0);
//        return balance1 - balance2;
//    }
//
//    /**
//     - Calculates the total balance for a single user.
//     - The balance is negative if the user owes money and positive if they are owed money.
//     -    - @param user The user to calculate the balance for.
//     - @return The total balance for the user.
//     */
//    public double getTotalBalance(User user) {
//        double total = 0.0
//        // Iterate through all user pairs and calculate the total
//        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
//            UserPair pair = entry.getKey();
//            double amount = entry.getValue();
//            if (pair.getUser1().equals(user)) {
//                total -= amount; // Money owed by the user (He has to give money)
//            } else if (pair.getUser2().equals(user)) {
//                total += amount; // Money owed to the user (He has to take money)
//            }
//        }
//        return total;
//    }
//
//    /**
//     - Simplifies the balances into a list of transactions to settle all debts.
//     - Simple and Straightforward implementation of the problem
//     - @return List of transactions needed to settle all debts.
//     */
//    public List<Transaction> getSimplifiedSettlements() {
//        // Step 1: Calculate net balances for each user
//        Map<User, Double> netBalances = new HashMap<>();
//        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
//            UserPair pair = entry.getKey();
//            double amount = entry.getValue();
//
//            User debtor = pair.getUser1(); // User who owes money (Who has to pay)
//            User creditor = pair.getUser2(); // User who is owed money (Who gets the money)
//            // Update the net balances for debtor and creditor
//            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
//            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
//        }
//
//        // Step 2: Separate users into debtors and creditors
//        List<User> debtors = new ArrayList<>();
//        List<User> creditors = new ArrayList<>();
//        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
//            User user = entry.getKey();
//            double balance = entry.getValue();
//            if (balance < 0) {
//                debtors.add(user); // Users who owe money (Who has to pay)
//            } else if (balance > 0) {
//                creditors.add(user); // Users who are owed money (Who gets the money back)
//            }
//        }
//
//        // Step 3: Match debtors and creditors to create transactions
//        List<Transaction> transactions = new ArrayList<>();
//        int debtorIndex = 0;
//        int creditorIndex = 0;
//
//        while (debtorIndex < debtors.size() && creditorIndex < creditors.size()) {
//            User debtor = debtors.get(debtorIndex);
//            User creditor = creditors.get(creditorIndex);
//            double debtorBalance = netBalances.get(debtor);
//            double creditorBalance = netBalances.get(creditor);
//
//            // Determine the transfer amount as the smaller of the two balances
//            double transferAmount = Math.min(Math.abs(debtorBalance), creditorBalance);
//
//            // Create a transaction for the transfer amount
//            transactions.add(new Transaction(debtor, creditor, transferAmount));
//
//            // Update net balances after the transaction
//            netBalances.put(debtor, debtorBalance + transferAmount);
//            netBalances.put(creditor, creditorBalance - transferAmount);
//
//            // Move to the next debtor or creditor if their balance is settled
//            if (Math.abs(netBalances.get(debtor)) < 0.001) {
//                debtorIndex++;
//            }
//            if (Math.abs(netBalances.get(creditor)) < 0.001) {
//                creditorIndex++;
//            }
//        }
//        return transactions;
//    }
//
//    /**
//     - Calculates the minimum number of transactions needed to settle all balances.
//     - Uses backtracking approach to find the solution.
//     -    - @return minimum count needed to settle all debts.
//     */
//    public int getSubOptimalMinimumSettlements() {
//        // Step 1: Calculate net balances for each user
//        Map<User, Double> netBalances = new HashMap<>();
//        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
//            UserPair pair = entry.getKey();
//            double amount = entry.getValue();
//            User debtor = pair.getUser1(); // The user who owes money
//            User creditor = pair.getUser2(); // The user who is owed money
//
//            // Update the net balance of each user
//            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
//            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
//        }
//        List<Double> creditList = new ArrayList<>();
//        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
//            if (Math.abs(entry.getValue()) > 0.001) { // Ignore near-zero balances
//                allUsers.add(entry.getKey()); // Store the user
//                creditList.add(entry.getValue()); // Store the net balance
//            }
//        }
//        int n = creditList.size(); // Total number of users with non-zero balance
//        return subOptimalDfs(0, creditList, n); // Call DFS to compute the minimum transactions
//    }
//
//    /**
//     - Recursively finds the minimum number of transactions required to settle debts.
//     - Uses a greedy approach by settling the current user's balance with future users.
//     -    - @param currentUserIndex Index of the user whose balance needs to be settled.
//     - @param creditList List of net balances for all users.
//     - @param n Number of users with non-zero balances.
//     - @return Minimum transactions required to settle all debts.
//     */
//    private int subOptimalDfs(int currentUserIndex, List<Double> creditList, int n) {
//        // Skip already settled users (those with zero balance)
//        while (currentUserIndex < n && creditList.get(currentUserIndex) == 0) {
//            currentUserIndex++;
//        }
//
//        // Base case: If all users have zero balance, no further transactions are needed
//        if (currentUserIndex == n)
//            return 0;
//        int cost = Integer.MAX_VALUE; // Variable to track the minimum number of transactions
//        // Try to settle currentUserBalance with a future user having an opposite balance
//        for (int nextIndex = currentUserIndex + 1; nextIndex < n; nextIndex++) {
//            // Ensure we only settle debts between users with opposite balances
//            if (creditList.get(nextIndex) * creditList.get(currentUserIndex) < 0) {
//                // Transfer current user's balance to the next valid user
//                creditList.set(nextIndex, creditList.get(nextIndex) + creditList.get(currentUserIndex));
//                // Recursively settle the remaining balances
//                cost = Math.min(cost, 1 + subOptimalDfs(currentUserIndex + 1, creditList, n));
//
//                // Backtrack: Undo the transaction to explore other possibilities
//                creditList.set(nextIndex, creditList.get(nextIndex) - creditList.get(currentUserIndex));
//            }
//        }
//        return cost; // Return the minimum transactions required
//    }
//
//
//    public int getOptimalMinimumSettlements() {
//        // Step 1: Calculate net balances for each user
//        Map<User, Double> netBalances = new HashMap<>();
//        for (Map.Entry<UserPair, Double> entry : balances.entrySet()) {
//            UserPair pair = entry.getKey();
//            double amount = entry.getValue();
//            User debtor = pair.getUser1(); // The user who owes money
//            User creditor = pair.getUser2(); // The user who is owed money
//            // Update the net balance of each user
//            netBalances.put(debtor, netBalances.getOrDefault(debtor, 0.0) - amount);
//            netBalances.put(creditor, netBalances.getOrDefault(creditor, 0.0) + amount);
//        }
//        List<Double> creditList = new ArrayList<>();
//
//
//        for (Map.Entry<User, Double> entry : netBalances.entrySet()) {
//            if (Math.abs(entry.getValue()) > 0.001) { // Ignore near-zero balances
//                creditList.add(entry.getValue()); // Store the net balance
//            }
//        }
//        // Step 3: Apply Dynamic Programming to find the minimum transactions required
//        int n = creditList.size(); // Number of users with non-zero balance
//        int[] dp = new int[1 << n]; // DP array for memoization
//        Arrays.fill(dp, -1);
//        dp[0] = 0; // Base case: No users left means zero transactions
//
//
//        // Find the maximum number of fully settled subgroups using DFS + DP
//        int maxSubGroups = dfs((1 << n) - 1, dp, creditList);
//
//        // Minimum transactions needed = Total users - Maximum fully settled groups
//        return n - maxSubGroups;
//    }
//    /**
//     - Helper method to calculate the sum of balances in a subset, given by a bitmask.
//     -
//     - @param values The list of credit balances.
//     - @param mask The bitmask representing a subset of users.
//     - @return The sum of balances in the subset.
//     */
//    private double sumOfMask(List<Double> values, int mask) {
//        double sum = 0;
//        for (int i = 0; i < values.size(); i++) {
//            if ((mask & (1 << i)) != 0) { // Check if the i-th bit is set in the mask
//                sum += values.get(i); // Add the corresponding balance to the sum
//            }
//        }
//        return sum;
//    }
//    /**
//     - DFS with memoization to determine the maximum number of balanced subgroups.
//     -
//     - @param mask Bitmask representing the remaining users.
//     - @param dp Memoization array for storing computed results.
//     - @param creditList List of net balances for each user.
//     - @return The maximum number of fully settled subgroups.
//     */
//    private int dfs(int mask, int[] dp, List<Double> creditList) {
//        if (mask == 0) // Base case: No users left to process
//            return 0;
//        if (dp[mask] != -1) // Return cached result if already computed
//            return dp[mask];
//        int maxSubGroups = 0;
//        int n = creditList.size();
//        // Try all possible subsets (submasks) of the current mask
//        for (int submask = 1; submask < (1 << n); submask++) {
//            // Check if submask is a subset of mask and sums to zero (i.e., can be settled)
//            if ((submask & mask) == submask && Math.abs(sumOfMask(creditList, submask)) < 0.001) {
//                // If a subset can be settled, find the remaining subgroups recursively
//                maxSubGroups = Math.max(maxSubGroups, 1 + dfs(mask ^ submask, dp, creditList));
//            }
//        }
//        dp[mask] = maxSubGroups; // Store result in memoization table
//        return maxSubGroups;
//    }
//}
//
//
//// Main class to demonstrate the system
//public class SplitwiseSystem {
//    public static void main(String[] args) {
//        // Create users
//        User alice = new User("u1", "Alice", "alice@example.com");
//        User bob = new User("u2", "Bob", "bob@example.com");
//        User charlie = new User("u3", "Charlie", "charlie@example.com");
//
//        // Create expense manager and balance sheet
//        ExpenseManager expenseManager = new ExpenseManager();
//        BalanceSheet balanceSheet = new BalanceSheet();
//
//        // Register the balance sheet as an observer
//        expenseManager.addObserver(balanceSheet);
//
//        // Create and add users to a list for expenses
//        List<User> participants = new ArrayList<>();
//        participants.add(alice);
//        participants.add(bob);
//        participants.add(charlie);
//
//        // Alice pays for dinner - Create an equal split expense
//        Split equalSplit = SplitFactory.createSplit("EQUAL");
//        Map<String, Object> splitDetails = new HashMap<>();
//        Map<User, Double> dinnerShares = equalSplit.calculateSplit(60.0, participants, splitDetails);
//
//        Expense dinnerExpense = new Expense("e1", "Dinner", 60.0, alice, participants, dinnerShares);
//
//        // Add the expense to the expense manager which will notify observers
//        expenseManager.addExpense(dinnerExpense);
//
//        // Bob pays for movie tickets - Create a percentage split expense
//        Map<String, Object> percentageSplitDetails = new HashMap<>();
//        Map<User, Double> percentages = new HashMap<>();
//        percentages.put(alice, 40.0);
//        percentages.put(bob, 30.0);
//        percentages.put(charlie, 30.0);
//        percentageSplitDetails.put("percentages", percentages);
//
//        Split percentageSplit = SplitFactory.createSplit("PERCENTAGE");
//        Map<User, Double> movieShares = percentageSplit.calculateSplit(45.0, participants, percentageSplitDetails);
//
//        Expense movieExpense = new Expense("e2", "Movie", 45.0, bob, participants, movieShares);
//
//        // Add the movie expense to the expense manager
//        expenseManager.addExpense(movieExpense);
//
//        // Get individual balances
//        System.out.println("Individual balances:");
//        System.out.println("Alice's total balance: $" + balanceSheet.getTotalBalance(alice));
//        System.out.println("Bob's total balance: $" + balanceSheet.getTotalBalance(bob));
//        System.out.println("Charlie's total balance: $" + balanceSheet.getTotalBalance(charlie));
//
//        // Print specific balances between users
//        System.out.println("nPairwise balances:");
//        System.out.println("Alice and Bob: $" + balanceSheet.getBalance(alice, bob));
//        System.out.println("Alice and Charlie: $" + balanceSheet.getBalance(alice, charlie));
//        System.out.println("Bob and Charlie: $" + balanceSheet.getBalance(bob, charlie));
//
//        // Get the simplified settlements
//        List<Transaction> settlements = balanceSheet.getSimplifiedSettlements();
//
//        // Display optimal minimum settlements (DP algorithm)
//        System.out.println("n=== OPTIMAL MINIMUM SETTLEMENTS ===");
//        int optimalSettlements = balanceSheet.getOptimalMinimumSettlements();
//        System.out.println(optimalSettlements);
//
//        // Print the settlements
//        System.out.println("nSimplified settlements:");
//        for (Transaction transaction : settlements) {
//            System.out.println(transaction.getFrom().getName() + " pays " +
//                    transaction.getTo().getName() + " $" +
//                    transaction.getAmount());
//        }
//    }
//}