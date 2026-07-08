import java.util.ArrayList;

public class Account {
    private String userId;
    private String pin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public Account(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() { return userId; }
    public boolean validatePin(String inputPin) { return this.pin.equals(inputPin); }
    public double getBalance() { return balance; }
    
    public void addTransaction(String type, double amount) {
        if (type.equals("Deposit") || type.equals("Received Transfer")) {
            balance += amount;
        } else {
            balance -= amount;
        }
        transactionHistory.add(new Transaction(type, amount));
    }

    public void printHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found in this session.");
        } else {
            for (Transaction t : transactionHistory) {
                System.out.println(t);
            }
        }
    }
}