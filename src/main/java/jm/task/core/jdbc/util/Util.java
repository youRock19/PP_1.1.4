package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    final static String DB_URL = "jdbc:mysql://localhost:3306/users";
    final static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    final static String DB_USER = "youRock19";
    final static String DB_PASS = "jUGlk826+3_year";
    final static String DB_DIALECT = "org.hibernate.dialect.MySQLDialect";
    private static Connection connection;
    private static SessionFactory sessionFactory;
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Properties properties = new Properties();
                properties.put(Environment.URL, DB_URL);
                properties.put(Environment.DRIVER, DB_DRIVER);
                properties.put(Environment.USER, DB_USER);
                properties.put(Environment.PASS, DB_PASS);
                properties.put(Environment.DIALECT, DB_DIALECT);
                //properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,
                        //"thread");
                sessionFactory = new Configuration()
                        .setProperties(properties)
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Ошибка создания SessionFactory");
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
