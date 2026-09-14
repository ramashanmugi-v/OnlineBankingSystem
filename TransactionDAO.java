import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // Add transaction
    public boolean addTransaction(
            String accountNumber,
            String transactionType,
            double amount,
            String description) {

        if (amount <= 0) {
            System.out.println(
                    "Transaction amount must be greater than zero.");
            return false;
        }

        String sql =
                "INSERT INTO transactions " +
                "(account_number, transaction_type, amount, description) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);
            statement.setString(4, description);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println(
                    "Transaction saving failed!");

            System.out.println(e.getMessage());

            return false;
        }
    }


    // Display transaction history
    public void displayTransactionHistory(
            String accountNumber) {

        String sql =
                "SELECT * FROM transactions " +
                "WHERE account_number = ? " +
                "ORDER BY transaction_date DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            ResultSet resultSet =
                    statement.executeQuery();

            boolean found = false;

            System.out.println(
                    "\n===== TRANSACTION HISTORY =====");

            while (resultSet.next()) {

                found = true;

                System.out.println(
                        "Transaction ID : "
                        + resultSet.getInt("id"));

                System.out.println(
                        "Type           : "
                        + resultSet.getString(
                                "transaction_type"));

                System.out.println(
                        "Amount         : Rs."
                        + resultSet.getDouble("amount"));

                System.out.println(
                        "Description    : "
                        + resultSet.getString(
                                "description"));

                System.out.println(
                        "Date & Time    : "
                        + resultSet.getTimestamp(
                                "transaction_date"));

                System.out.println(
                        "------------------------------");
            }

            if (!found) {

                System.out.println(
                        "No transactions found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Transaction history failed!");

            System.out.println(e.getMessage());
        }
    }


    // Filter transactions by type
    public void filterByType(
            String accountNumber,
            String transactionType) {

        String sql =
                "SELECT * FROM transactions " +
                "WHERE account_number = ? " +
                "AND transaction_type = ? " +
                "ORDER BY transaction_date DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, transactionType);

            ResultSet resultSet =
                    statement.executeQuery();

            boolean found = false;

            System.out.println(
                    "\n===== "
                    + transactionType.toUpperCase()
                    + " TRANSACTIONS =====");

            while (resultSet.next()) {

                found = true;

                System.out.println(
                        "Transaction ID : "
                        + resultSet.getInt("id"));

                System.out.println(
                        "Type           : "
                        + resultSet.getString(
                                "transaction_type"));

                System.out.println(
                        "Amount         : Rs."
                        + resultSet.getDouble("amount"));

                System.out.println(
                        "Description    : "
                        + resultSet.getString(
                                "description"));

                System.out.println(
                        "Date & Time    : "
                        + resultSet.getTimestamp(
                                "transaction_date"));

                System.out.println(
                        "------------------------------");
            }

            if (!found) {

                System.out.println(
                        "No "
                        + transactionType
                        + " transactions found.");
            }

        } catch (SQLException e) {

            System.out.println(
                    "Transaction filter failed!");

            System.out.println(e.getMessage());
        }
    }
}