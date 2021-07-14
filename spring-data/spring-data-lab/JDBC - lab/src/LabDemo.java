import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class LabDemo {
    public static void main(String[] args) {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni","root", "root");
            PreparedStatement statement =
                    connection.prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");
            Scanner scan = new Scanner(System.in);
            statement.setDouble(1, Double.parseDouble(scan.nextLine()));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%s %s%n" ,resultSet.getString(1),resultSet.getString(2));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
