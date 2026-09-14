import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO {

    // Generate unique account number
    public String generateAccountNumber() {

        String accountNumber;

        do {
            int number =
                    100000 + (int) (Math.random() * 900000);

            accountNumber = "ACC" + number;

        } while (accountExists(accountNumber));

        return accountNumber;
    }


    // Create new bank account
    public boolean createAccount(
            String accountNumber,
            String accountHolderName,
            String accountType,
            double balance) {

        if (balance < 0) {
            System.out.println(
                    "Balance cannot be negative.");
            return false;
        }

        String sql =
                "INSERT INTO accounts " +
                "(account_number, account_holder_name, account_type, balance) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, accountHolderName);
            statement.setString(3, accountType);
            statement.setDouble(4, balance);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Account creation failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }


    // Check whether account exists
    public boolean accountExists(String accountNumber) {

        String sql =
                "SELECT * FROM accounts " +
                "WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet resultSet =
                    statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {

            System.out.println(
                    "Account search failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }


    // View account details
    public void viewAccount(String accountNumber) {

        String sql =
                "SELECT * FROM accounts " +
                "WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "\n===== ACCOUNT DETAILS =====");

                System.out.println(
                        "Account Number : "
                        + resultSet.getString(
                                "account_number"));

                System.out.println(
                        "Account Holder : "
                        + resultSet.getString(
                                "account_holder_name"));

                System.out.println(
                        "Account Type   : "
                        + resultSet.getString(
                                "account_type"));

                System.out.println(
                        "Balance        : Rs."
                        + resultSet.getDouble(
                                "balance"));

            } else {

                System.out.println(
                        "Account not found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Account details failed!");

            System.out.println(e.getMessage());
        }
    }


    // Check balance
    public void checkBalance(String accountNumber) {

        String sql =
                "SELECT balance FROM accounts " +
                "WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet resultSet =
                    statement.executeQuery();

            if (resultSet.next()) {

                System.out.println(
                        "Current Balance: Rs."
                        + resultSet.getDouble(
                                "balance"));

            } else {

                System.out.println(
                        "Account not found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Balance check failed!");

            System.out.println(e.getMessage());
        }
    }


    // Deposit money + save transaction
    public boolean depositMoney(
            String accountNumber,
            double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Deposit amount must be greater than zero.");

            return false;
        }

        String updateSql =
                "UPDATE accounts " +
                "SET balance = balance + ? " +
                "WHERE account_number = ?";

        String transactionSql =
                "INSERT INTO transactions " +
                "(account_number, transaction_type, amount, description) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 updateSql)) {

                statement.setDouble(1, amount);
                statement.setString(2, accountNumber);

                int rowsUpdated =
                        statement.executeUpdate();

                if (rowsUpdated == 0) {

                    System.out.println(
                            "Account not found.");

                    connection.rollback();

                    return false;
                }
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 transactionSql)) {

                statement.setString(1, accountNumber);
                statement.setString(2, "DEPOSIT");
                statement.setDouble(3, amount);
                statement.setString(
                        4,
                        "Money deposited");

                statement.executeUpdate();
            }

            connection.commit();

            System.out.println(
                    "Amount deposited successfully.");

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Deposit failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }


    // Withdraw money + save transaction
    public boolean withdrawMoney(
            String accountNumber,
            double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Withdrawal amount must be greater than zero.");

            return false;
        }

        String balanceSql =
                "SELECT balance FROM accounts " +
                "WHERE account_number = ?";

        String updateSql =
                "UPDATE accounts " +
                "SET balance = balance - ? " +
                "WHERE account_number = ?";

        String transactionSql =
                "INSERT INTO transactions " +
                "(account_number, transaction_type, amount, description) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            double currentBalance;

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 balanceSql)) {

                statement.setString(1, accountNumber);

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Account not found.");

                    connection.rollback();

                    return false;
                }

                currentBalance =
                        resultSet.getDouble("balance");
            }

            if (amount > currentBalance) {

                System.out.println(
                        "Insufficient balance.");

                connection.rollback();

                return false;
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 updateSql)) {

                statement.setDouble(1, amount);
                statement.setString(2, accountNumber);

                statement.executeUpdate();
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 transactionSql)) {

                statement.setString(1, accountNumber);
                statement.setString(2, "WITHDRAWAL");
                statement.setDouble(3, amount);
                statement.setString(
                        4,
                        "Money withdrawn");

                statement.executeUpdate();
            }

            connection.commit();

            System.out.println(
                    "Amount withdrawn successfully.");

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Withdrawal failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }


    // Transfer money + save transactions
    public boolean transferMoney(
            String senderAccount,
            String receiverAccount,
            double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Transfer amount must be greater than zero.");

            return false;
        }

        if (senderAccount.equals(receiverAccount)) {

            System.out.println(
                    "Cannot transfer to the same account.");

            return false;
        }

        String balanceSql =
                "SELECT balance FROM accounts " +
                "WHERE account_number = ?";

        String updateSenderSql =
                "UPDATE accounts " +
                "SET balance = balance - ? " +
                "WHERE account_number = ?";

        String updateReceiverSql =
                "UPDATE accounts " +
                "SET balance = balance + ? " +
                "WHERE account_number = ?";

        String transactionSql =
                "INSERT INTO transactions " +
                "(account_number, transaction_type, amount, description) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection()) {

            connection.setAutoCommit(false);

            double senderBalance;

            // Check sender account
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 balanceSql)) {

                statement.setString(1, senderAccount);

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Sender account not found.");

                    connection.rollback();

                    return false;
                }

                senderBalance =
                        resultSet.getDouble("balance");
            }

            // Check balance
            if (amount > senderBalance) {

                System.out.println(
                        "Insufficient balance.");

                connection.rollback();

                return false;
            }

            // Check receiver account
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 balanceSql)) {

                statement.setString(1, receiverAccount);

                ResultSet resultSet =
                        statement.executeQuery();

                if (!resultSet.next()) {

                    System.out.println(
                            "Receiver account not found.");

                    connection.rollback();

                    return false;
                }
            }

            // Deduct from sender
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 updateSenderSql)) {

                statement.setDouble(1, amount);
                statement.setString(2, senderAccount);

                statement.executeUpdate();
            }

            // Add to receiver
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 updateReceiverSql)) {

                statement.setDouble(1, amount);
                statement.setString(2, receiverAccount);

                statement.executeUpdate();
            }

            // Sender transaction
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 transactionSql)) {

                statement.setString(1, senderAccount);
                statement.setString(2, "TRANSFER");
                statement.setDouble(3, amount);

                statement.setString(
                        4,
                        "Transferred to account "
                        + receiverAccount);

                statement.executeUpdate();
            }

            // Receiver transaction
            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 transactionSql)) {

                statement.setString(1, receiverAccount);
                statement.setString(2, "TRANSFER");
                statement.setDouble(3, amount);

                statement.setString(
                        4,
                        "Received from account "
                        + senderAccount);

                statement.executeUpdate();
            }

            connection.commit();

            System.out.println(
                    "Money transferred successfully!");

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Transfer failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }
}