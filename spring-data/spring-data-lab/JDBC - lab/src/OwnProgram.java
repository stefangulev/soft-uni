import java.sql.*;
import java.util.Scanner;

public class OwnProgram {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT u.user_name, u.first_name, u.last_name, COUNT(ug.game_id) AS games_count FROM users AS u JOIN users_games AS ug ON u.id = ug.user_id GROUP BY ug.user_id HAVING u.user_name = ?");

        Scanner scan = new Scanner(System.in);
        statement.setString(1, scan.nextLine());
        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()) {
            System.out.printf("User: %s%n", resultSet.getString("user_name"));
            System.out.printf("%s %s has played %d games",
                    resultSet.getString("first_name"), resultSet.getString("last_name"), resultSet.getInt("games_count"));
        } else {
            System.out.println("No such user exists");
        }

    }
}
