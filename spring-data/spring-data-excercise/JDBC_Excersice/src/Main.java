import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static Connection connection;

    public static void main(String[] args) throws SQLException {
        connection = getConnection();
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter problem number:");
        int problemNumber = Integer.parseInt(scan.nextLine());
        switch (problemNumber) {
            case 2:
                problemTwo();
                break;
            case 3:
                problemThree();
                break;
            case 4:
                problemFour();
                break;
            case 5:
                problemFive();
                break;
            case 6:
                problemSix();
                break;
            case 7:
                problemSeven();
                break;
            case 8:
                problemEight();
                break;
            case 9:
                problemNine();
                break;
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
    }

    public static void problemTwo() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT mv.minion_id) AS minion_count FROM minions_villains AS mv\n" +
                "JOIN villains AS v on mv.villain_id = v.id\n" +
                "GROUP BY v.name\n" +
                "HAVING minion_count > 15\n" +
                "ORDER BY minion_count DESC;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }

    public static void problemThree() throws SQLException {
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

    public static void problemFour() throws SQLException {
        Scanner scan = new Scanner(System.in);
        String[] minionInfo = scan.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String townName = minionInfo[3];
        String villainName = scan.nextLine().split(" ")[1];
        try {

            connection.setAutoCommit(false);
            ResultSet townResultSet = getIdByName("towns", townName).executeQuery();
            insertRowIfNonExistent(townResultSet, "towns", townName);
            ResultSet villainResultSet = getIdByName("villains", villainName).executeQuery();
            insertRowIfNonExistent(villainResultSet, "villains", villainName);

            ResultSet townIdResult = getIdByName("towns", townName).executeQuery();
            int townId = 0;
            if (townIdResult.next()) {
                townId = townIdResult.getInt("id");
            }
            insertRow("minions", minionName, minionAge, townId);
            ResultSet villianIDResult = getIdByName("villains", villainName).executeQuery();
            int villainID = 0;
            if (villianIDResult.next()) {
                villainID = villianIDResult.getInt("id");
            }
            ResultSet minionIdResult = getIdByName("minions", minionName).executeQuery();
            int minionId = 0;
            if (minionIdResult.next()) {
                minionId = minionIdResult.getInt("id");
            }
            insertRow("minions_villains", minionId, villainID, minionName, villainName);

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }
    }

    public static void problemFive() throws SQLException {
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

    private static void problemSix() throws SQLException {
        connection.setAutoCommit(false);
        Scanner scan = new Scanner(System.in);
        try {
            int id = Integer.parseInt(scan.nextLine());
            String villainName = getNameById(id, "villains");
            if (villainName.equals("")) {
                throw new SQLException();
            }
            int releasedMinions = deleteById(id, "minions_villains", "villain_id");
            deleteById(id, "villains", "id");
            System.out.printf("%s was deleted%n", villainName);
            System.out.printf("%d minions released%n", releasedMinions);
            connection.commit();
        } catch (SQLException ex) {
            connection.rollback();
            System.out.println("No such villain was found");
        }

    }

    private static void problemSeven() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT `name` FROM minions");
        ResultSet resultSet = statement.executeQuery();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString(1));
        }
        while (!names.isEmpty()) {
            System.out.println(names.remove(0));
            System.out.println(names.remove(names.size() - 1));
        }
    }

    private static void problemEight() throws SQLException {
        Scanner scan = new Scanner(System.in);
        int[] ids = Arrays.stream(scan.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < ids.length; i++) {
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

    private static void problemNine() throws SQLException {
        CallableStatement statement = connection.prepareCall("CALL usp_get_older(?)");
        Scanner scan = new Scanner(System.in);
        statement.setInt(1, Integer.parseInt(scan.nextLine()));
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }
    }

    public static PreparedStatement getIdByName(String tableName, String name) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM " + tableName + " WHERE `name` = ?");
        statement.setString(1, name);
        return statement;
    }

    public static void insertRowIfNonExistent(ResultSet resultSet, String tableName, String name) throws SQLException {
        if (!resultSet.next()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + " (`name`) VALUES (?)");
            statement.setString(1, name);
            statement.execute();
            String result = tableName.equals("towns") ? String.format("Town %s was added to the database.%n", name) :
                    String.format("Villain %s was added to the database.%n", name);
            System.out.println(result);
        }
    }

    public static void insertRow(String tableName, String name, int age, int town_id) throws SQLException {

        PreparedStatement statement = connection.prepareStatement("INSERT INTO " + tableName + " (`name`, age, town_id) VALUES (?, ? ,?)");
        statement.setString(1, name);
        statement.setInt(2, age);
        statement.setInt(3, town_id);
        statement.execute();
        if (tableName.equals("towns")) {
            System.out.printf("Town %s was added to the database.%n", name);
        } else if (tableName.equals("villains")) System.out.printf("Villain %s was added to the database.%n", name);
    }

    public static void insertRow(String tableName, int minion_id, int villain_id, String minionName, String villainName) throws SQLException {

        PreparedStatement setMinionServant = connection.prepareStatement("INSERT INTO " + tableName + " (minion_id, villain_id) VALUES (? , ?)");
        setMinionServant.setInt(1, minion_id);
        setMinionServant.setInt(2, villain_id);
        setMinionServant.execute();
        System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
    }

    public static int deleteById(int id, String tableName, String columnName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM " + tableName + " WHERE " + columnName + " = ?");
        statement.setInt(1, id);
        return statement.executeUpdate();
    }

    public static String getNameById(int id, String tableName) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT `name` FROM " + tableName + " WHERE id = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        String result = "";
        if (resultSet.next()) {
            result = resultSet.getString("name");
        }
        return result;

    }
}
