import java.sql.*;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        Scanner scan = new Scanner(System.in);
        statement.setDouble(1, Double.parseDouble(scan.nextLine()));
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(9));
        }
    }
}
