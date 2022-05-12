package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users "
                + "(id BIGINT PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(255) NOT NULL, "
                + "lastName VARCHAR(255) NOT NULL, "
                + "age TINYINT NOT NULL)";
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка создания таблицы в БД");
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления таблицы из БД");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
            System.out.println("User with name " + name + " added to database");
        } catch (Exception e) {
            System.out.println("Ошибка сохранения объекта в БД");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(session.get(User.class, id));
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка удаления объекта из БД");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        String hql = "from User";
        List<User> list = null;
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            list = session.createQuery(hql, User.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка получения объектов из БД");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        String hql = "delete User";
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createQuery(hql).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Ошибка очистки таблицы в БД");
            e.printStackTrace();
        }
    }
}
