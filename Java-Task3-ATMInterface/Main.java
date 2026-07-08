public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        
        // Setup two test accounts
        bank.addAccount(new Account("user123", "4321", 25000.0));
        bank.addAccount(new Account("user456", "7890", 5000.0));

        ATM atm = new ATM(bank);
        atm.start();
    }
}