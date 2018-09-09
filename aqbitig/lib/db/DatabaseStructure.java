package aqbitig.lib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class DatabaseStructure {

    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:gizehter.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            String sql = "CREATE TABLE IF NOT EXISTS `atomic` ("
                    + "	`id` INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	`level` INTEGER NOT NULL,"
                    + "	`index` INTEGER NOT NULL,"
                    + "	`path` TEXT NOT NULL,"
                    + "	`login` TEXT,"
                    + "	`password` TEXT,"
                    + "	`url` TEXT,"
                    + "	`comment` TEXT"
                    + ");";
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
}
