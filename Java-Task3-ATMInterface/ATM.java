import java.util.Scanner;

public class ATM {
    private Bank bank;
    private Account currentAccount;
    private Scanner scanner = new Scanner(System.in);

    public ATM(Bank bank) { this.bank = bank; }

    public void start() {
        System.out.println("=== Welcome to the ATM System ===");
        int attempts = 0;
        
        while (attempts < 3) {
            System.out.print("Enter User ID: ");
            String id = scanner.next();
            System.out.print("Enter PIN: ");
            String pin = scanner.next();

            Account acc = bank.getAccount(id);
            if (acc != null && acc.validatePin(pin)) {
                currentAccount = acc;
                showMenu();
                return;
            }
            attempts++;
            System.out.println("Invalid credentials! Attempts remaining: " + (3 - attempts));
        }
        System.out.println("Access Denied. Account Locked.");
    }

    private void showMenu() {
        int choice;
        do {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Transaction History\n2. Withdraw\n3. Deposit\n4. Transfer\n5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1: currentAccount.printHistory(); break;
                case 2: handleWithdraw(); break;
                case 3: handleDeposit(); break;
                case 4: handleTransfer(); break;
                case 5: System.out.println("Session closed. Goodbye!"); break;
                default: System.out.println("Invalid entry.");
            }
        } while (choice != 5);
    }

    private void handleWithdraw() {
        System.out.print("Enter withdrawal amount: \u20B9");
        double amt = scanner.nextDouble();
        if (amt > currentAccount.getBalance()) {
            System.out.println("Insufficient Funds!");
        } else {
            currentAccount.addTransaction("Withdrawal", amt);
            System.out.println("Please collect your cash.");
        }
    }

    private void handleDeposit() {
        System.out.print("Enter deposit amount: \u20B9");
        double amt = scanner.nextDouble();
        currentAccount.addTransaction("Deposit", amt);
        System.out.println("Deposit successful.");
    }

    private void handleTransfer() {
        System.out.print("Enter recipient Account ID: ");
        String targetId = scanner.next();
        Account recipient = bank.getAccount(targetId);

        if (recipient == null) {
            System.out.println("Recipient account not found.");
            return;
        }

        System.out.print("Enter transfer amount: \u20B9");
        double amt = scanner.nextDouble();
        if (amt > currentAccount.getBalance()) {
            System.out.println("Insufficient Funds!");
        } else {
            currentAccount.addTransaction("Transfer to " + targetId, amt);
            recipient.addTransaction("Received Transfer", amt);
            System.out.println("Transfer successful.");
        }
    }
}