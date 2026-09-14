import java.util.ArrayList;

public class BankService {

    private ArrayList<BankAccount> accounts;
    private ArrayList<Transaction> transactions;

    public BankService() {
        accounts = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public boolean addAccount(BankAccount account) {

        if (findAccount(account.getAccountNumber()) != null) {
            return false;
        }

        accounts.add(account);
        return true;
    }

    public BankAccount findAccount(String accountNumber) {

        for (BankAccount account : accounts) {

            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

    public void addTransaction(
            String type,
            double amount,
            String description) {

        Transaction transaction =
                new Transaction(type, amount, description);

        transactions.add(transaction);
    }

    public void transferMoney(
            BankAccount sender,
            BankAccount receiver,
            double amount) {

        if (sender == null || receiver == null) {
            System.out.println("Account does not exist.");
            return;
        }

        if (sender.getAccountNumber()
                .equals(receiver.getAccountNumber())) {

            System.out.println(
                    "Cannot transfer money to the same account."
            );
            return;
        }

        if (amount <= 0) {
            System.out.println(
                    "Transaction amount must be greater than zero."
            );
            return;
        }

        if (amount > sender.getBalance()) {
            System.out.println("Insufficient balance.");
            return;
        }

        sender.withdraw(amount);
        receiver.deposit(amount);

        addTransaction(
                "TRANSFER",
                amount,
                "Transferred to account "
                        + receiver.getAccountNumber()
        );

        System.out.println("Transfer successful!");
    }

    public void displayTransactionHistory() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        System.out.println("\n===== TRANSACTION HISTORY =====");

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }
    }

    public void filterByType(String type) {

        boolean found = false;

        System.out.println(
                "\n===== " + type.toUpperCase()
                        + " TRANSACTIONS ====="
        );

        for (Transaction transaction : transactions) {

            if (transaction.getType()
                    .equalsIgnoreCase(type)) {

                transaction.displayTransaction();
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No transactions found for type: " + type
            );
        }
    }
}