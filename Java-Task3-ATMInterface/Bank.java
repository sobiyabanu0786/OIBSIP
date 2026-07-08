import java.util.HashMap;

public class Bank {
    private HashMap<String, Account> accounts = new HashMap<>();

    public void addAccount(Account account) {
        accounts.put(account.getUserId(), account);
    }

    public Account getAccount(String userId) {
        return accounts.get(userId);
    }
}