import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public boolean registerUser(String username, String password) {

        String sql =
                "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            statement.executeUpdate();

            return true;

        } catch (SQLException e) {

            System.out.println("Registration failed!");
            System.out.println(e.getMessage());

            return false;
        }
    }

    public boolean loginUser(String username, String password) {

        String sql =
                "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {

            System.out.println("Login failed!");
            System.out.println(e.getMessage());

            return false;
        }
    }
}