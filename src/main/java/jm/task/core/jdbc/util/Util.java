package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    final static String DB_URL = "jdbc:mysql://localhost:3306/users";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection connection;
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, "youRock19", "jUGlk826+3_year");
    } catch (ClassNotFoundException e) {
            e.printStackTrace();
    }
        return connection;
    }
}
