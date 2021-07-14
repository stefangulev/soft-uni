import javax.print.DocFlavor;
import java.sql.*;
import java.util.Scanner;

public class AddMinions {
    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);
        String[] minionInfo = scan.nextLine().split(" ");
        String minionName = minionInfo[1];
        int minionAge = Integer.parseInt(minionInfo[2]);
        String townName = minionInfo[3];
        String villainName = scan.nextLine().split(" ")[1];
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", "root", "root");
        try {

            connection.setAutoCommit(false);
            PreparedStatement findTownStatement = connection.prepareStatement("SELECT id FROM towns WHERE `name` = ?");
            findTownStatement.setString(1, townName);
            ResultSet townResultSet = findTownStatement.executeQuery();
            if (!townResultSet.next()) {
                PreparedStatement townStatement = connection.prepareStatement("INSERT INTO towns (`name`) VALUES (?)");
                townStatement.setString(1, townName);
                townStatement.execute();
                System.out.printf("Town %s was added to the database.%n", townName);
            }
            PreparedStatement findVillainStatement = connection.prepareStatement("SELECT id FROM villains WHERE `name` = ?");
            findVillainStatement.setString(1, villainName);
            ResultSet villainResultSet = findVillainStatement.executeQuery();
            if (!villainResultSet.next()) {
                PreparedStatement villainStatement = connection.prepareStatement("INSERT INTO villains (name, evilness_factor) VALUES (?, ?)");
                villainStatement.setString(1, villainName);
                villainStatement.setString(2, "evil");
                villainStatement.execute();
                System.out.printf("Villain %s was added to the database.%n", villainName);
            }
            PreparedStatement getTownId = connection.prepareStatement("SELECT * FROM towns WHERE name = ? ");
            getTownId.setString(1, townName);
            ResultSet townIdResult = getTownId.executeQuery();
            int townId = 0;
            if(townIdResult.next()) {
                townId = townIdResult.getInt("id");
            }
            PreparedStatement addMinion = connection.prepareStatement("INSERT INTO minions (`name`, age, town_id) VALUES (?,?,?)");
            addMinion.setString(1, minionName);
            addMinion.setInt(2, minionAge);
            addMinion.setInt(3, townId);
            addMinion.execute();
            PreparedStatement getVillainID = connection.prepareStatement("SELECT * FROM villains WHERE name = ? ");
            getVillainID.setString(1, villainName);
            ResultSet villianIDResult = getVillainID.executeQuery();
            int villainID = 0;
            if(villianIDResult.next()) {
                villainID = villianIDResult.getInt("id");
            }
            PreparedStatement getMinionId = connection.prepareStatement("SELECT id FROM minions WHERE `name` = ?");
            getMinionId.setString(1, minionName);
            ResultSet minionIdResult = getMinionId.executeQuery();
            int minionId = 0;
            if (minionIdResult.next()) {
                minionId= minionIdResult.getInt("id");
            }
            PreparedStatement setMinionServant = connection.prepareStatement("INSERT INTO minions_villains (minion_id, villain_id) VALUES (? , ?)");
            setMinionServant.setInt(1, minionId);
            setMinionServant.setInt(2, villainID);
            setMinionServant.execute();
            System.out.printf("Successfully added %s to be minion of %s", minionName, villainName);
            connection.commit();
        } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
        }
    }
}
