import java.sql.*;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT m.name, m.age, v.name AS 'villain_name' FROM minions AS m\n" +
                "JOIN minions_villains AS mv ON mv.minion_id = m.id\n" +
                "JOIN villains AS v ON v.id = mv.villain_id\n" +
                "WHERE v.id = ?");
        Scanner scan = new Scanner(System.in);
        int villainId = Integer.parseInt(scan.nextLine());
        statement.setInt(1, villainId);
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            System.out.printf("Villain: %s%n1. %s %d%n", resultSet.getString("villain_name"), resultSet.getString("name"), resultSet.getInt("age"));
            int count = 1;
            while (resultSet.next()) {
                count++;
                System.out.printf("%d. %s %d%n", count, resultSet.getString("name"), resultSet.getInt("age"));
            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villainId);
        }
    }
}
