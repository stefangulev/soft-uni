import javax.print.DocFlavor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT `name` FROM minions");
        ResultSet resultSet = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString(1));
        }
        while (!names.isEmpty()) {
            System.out.println(names.remove(0));
            System.out.println(names.remove(names.size()-1));
        }


    }
}
