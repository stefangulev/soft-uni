import java.sql.*;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        PreparedStatement statement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT mv.minion_id) AS minion_count FROM minions_villains AS mv\n" +
                "JOIN villains AS v on mv.villain_id = v.id\n" +
                "GROUP BY v.name\n" +
                "HAVING minion_count > 15\n" +
                "ORDER BY minion_count DESC;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+ " " + resultSet.getString(2));
        }


    }
}
