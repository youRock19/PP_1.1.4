import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserServiceTest {
    private final UserService userService = new UserServiceImpl();

    private final String testName = "Ivan";
    private final String testLastName = "Ivanov";
    private final byte testAge = 5;


    @Test
    public void dropUsersTable() {
        try {
            userService.dropUsersTable();
            userService.dropUsersTable();
        } catch (Exception e) {
            Assert.fail("��� ������������ �������� ������� ��������� ����������\n" + e);
        }
    }

    @Test
    public void createUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
        } catch (Exception e) {
            Assert.fail("��� ������������ �������� ������� ������������� ��������� ����������\n" + e.getMessage());
        }
    }

    @Test
    public void saveUser() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge);

            User user = userService.getAllUsers().get(0);

            if (!testName.equals(user.getName())
                    || !testLastName.equals(user.getLastName())
                    || testAge != user.getAge()
            ) {
                Assert.fail("User ��� ����������� �������� � ���� ������");
            }

        } catch (Exception e) {
            Assert.fail("�� ����� ������������ ���������� ������������ ��������� ����������\n" + e);
        }
    }

    @Test
    public void removeUserById() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge);
            userService.removeUserById(1L);
        } catch (Exception e) {
            Assert.fail("��� ������������ �������� ������������ �� id ��������� ����������\n" + e);
        }
    }

    @Test
    public void getAllUsers() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge);
            List<User> userList = userService.getAllUsers();

            if (userList.size() != 1) {
                Assert.fail("��������� ������������ ������ ������ ���������� ������������/�������� ��� �������� �������");
            }
        } catch (Exception e) {
            Assert.fail("��� ������� ������� ���� ������������� �� ���� ������ ��������� ����������\n" + e);
        }
    }

    @Test
    public void cleanUsersTable() {
        try {
            userService.dropUsersTable();
            userService.createUsersTable();
            userService.saveUser(testName, testLastName, testAge);
            userService.cleanUsersTable();

            if (userService.getAllUsers().size() != 0) {
                Assert.fail("����� �������� ������� ������������� ���������� �� ���������");
            }
        } catch (Exception e) {
            Assert.fail("��� ������������ ������� ������� ������������� ��������� ����������\n" + e);
        }
    }

}
