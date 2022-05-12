package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("LEO", "TURTLES", (byte) 30);
        userService.saveUser("RAF", "TURTLES", (byte) 30);
        userService.saveUser("MIKE", "TURTLES", (byte) 30);
        userService.saveUser("DON", "TURTLES", (byte) 30);
        for  (User user: userService.getAllUsers()) {
            System.out.println(user.toString());
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
        Util.getSessionFactory().close();
    }
}
