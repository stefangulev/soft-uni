import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        Scanner scan = new Scanner(System.in);
        int[] ids = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i <ids.length; i++) {
            PreparedStatement updateRows = connection.prepareStatement("UPDATE minions\n" +
                    "SET `age` = age +1, `name` = LOWER(`name`)\n" +
                    "WHERE id = ?;");
            updateRows.setInt(1, ids[i]);
            updateRows.execute();
        }
        PreparedStatement getData = connection.prepareStatement("SELECT `name`, `age` FROM minions");
        ResultSet resultSet = getData.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("name") + " " + resultSet.getInt(2));
        }
    }
}
