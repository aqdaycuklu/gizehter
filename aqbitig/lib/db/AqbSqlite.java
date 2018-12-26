package aqbitig.lib.db;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.gizehter.view.Main;
import aqbitig.lib.basic.C;
import aqbitig.lib.basic.T;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * gizehter = giz(li) + anahtar
 *
 * @author aqdaycuklu
 */
public class AqbSqlite {

    public static void insert(String sql) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AqbSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection = null;
        try {

            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.filePath);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            int rs = statement.executeUpdate(sql);
            T.o("i: " + rs);

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

    public static void query(String sql) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AqbSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.file);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                T.o("path = " + rs.getString("path"));
                T.o("id = " + rs.getInt("id"));
            }
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

    public static List<MyAtomic> load() {
        List<MyAtomic> myAtomicSet = new ArrayList<>();
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.file);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from atomic");

            while (rs.next()) {
                MyAtomic myAtomic = new MyAtomic(
                        rs.getString("path"),
                        C.decrypt(Main.password, rs.getString("login")),
                        C.decrypt(Main.password, rs.getString("password")),
                        C.decrypt(Main.password, rs.getString("url")),
                        C.decrypt(Main.password, rs.getString("comment"))
                );
                myAtomicSet.add(myAtomic);
            }

            return myAtomicSet;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AqbSqlite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return null;
    }

    public static void truncate() {
        insert("DELETE FROM `atomic`; UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='atomic';");
    }

    private static void createDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AqbSqlite.class.getName()).log(Level.SEVERE, null, ex);
        }

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.file);
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

            sql = "CREATE TABLE IF NOT EXISTS `settings` ("
                    + "	`id` INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "	`key` TEXT NOT NULL,"
                    + "	`value` TEXT NOT NULL"
                    + ");";

            System.out.println(statement.executeUpdate(sql));

            sql = "INSERT OR IGNORE INTO `settings`"
                    + " (`key`, `value`)"
                    + " VALUES"
                    + "('password', '" + C.sha(Main.password) + "')";

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

    public static boolean checkPassword(String password) {
        Connection connection = null;

        try {

            password = C.sha(password);

            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:" + Main.file);
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            String sql = "SELECT `value` FROM `settings` WHERE `key` = 'password' AND `value` = '" + password + "';";
            T.o("sql: " + sql);
            ResultSet rs = statement.executeQuery(sql);
            T.o(rs);
            boolean ret = false;
            while (rs.next()) {
                String secretKey = rs.getString("value");
                T.o("sql: " + sql + " | sk: " + secretKey + " | password: " + password);
                if (secretKey.equals(password)) {
                    ret = true;
                }
            }

            return ret;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AqbSqlite.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return false;
    }

    public static boolean setPassword(String password) {
        if (!Main.file.exists() && "new".equals(Main.mode)) {
            AqbSqlite.createDatabase();
            return checkPassword(password);
        }
        return false;
    }

}
