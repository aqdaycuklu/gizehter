package aqbitig.lib.db;

import aqbitig.gizehter.model.MyAtomic;
import aqbitig.lib.T;
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
            connection = DriverManager.getConnection("jdbc:sqlite:gizehter.db");
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
            connection = DriverManager.getConnection("jdbc:sqlite:gizehter.db");
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

    public static List<MyAtomic> getAll() {
        List<MyAtomic> myAtomicSet = new ArrayList<MyAtomic>();
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");

            connection = DriverManager.getConnection("jdbc:sqlite:gizehter.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);
            ResultSet rs = statement.executeQuery("select * from atomic");

            while (rs.next()) {
                MyAtomic myAtomic = new MyAtomic(
                        rs.getString("path"),
                        rs.getString("login"),
                        rs.getString("password"),
                        rs.getString("url"),
                        rs.getString("comment")
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

}
