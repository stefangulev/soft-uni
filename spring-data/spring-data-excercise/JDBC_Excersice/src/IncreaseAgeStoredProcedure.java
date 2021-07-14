import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    //USE CALLABLESTATEMET INSTEAD OF PREPARED STATEMENT
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        CallableStatement statement = connection.prepareCall("CALL usp_get_older(?)");
        Scanner scan = new Scanner(System.in);
        statement.setInt(1, Integer.parseInt(scan.nextLine()));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }

    }
}
