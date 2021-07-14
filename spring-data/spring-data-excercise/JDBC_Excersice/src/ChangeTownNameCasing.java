import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ChangeTownNameCasing {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        PreparedStatement statement = connection.prepareStatement("UPDATE towns\n" +
                "SET `name` = UPPER(`name`)\n" +
                "WHERE `country` = ?;");
        Scanner scan = new Scanner(System.in);
        String country = scan.nextLine();
        statement.setString(1, country);
        statement.execute();
       PreparedStatement getResult = connection.prepareStatement("SELECT `name` FROM towns WHERE country = ?");
        getResult.setString(1, country);
        ResultSet result = getResult.executeQuery();
        List<String> toPrint = new ArrayList<>();
        while (result.next()) {
           toPrint.add(result.getString(1));
       }
        if (toPrint.size() > 0) {
            System.out.printf("%d town names were affected.%n", toPrint.size());
            System.out.printf("[%s]", String.join(", ", toPrint));
        } else {
            System.out.println("No town names were affected.");
        }


    }
}