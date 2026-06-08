package com.example.project.repository;

import com.example.project.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UserRepository extends BaseRepository<User, Integer> {

    public UserRepository(SessionFactory sessionFactory) {
        super(sessionFactory, User.class);
    }

    // Дополнительные методы, специфичные для User

    // Поиск пользователя по email
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResult();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске пользователя по email: " + email, e);
        }
    }

    // Поиск пользователей по имени
    public List<User> findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE name LIKE :name", User.class);
            query.setParameter("name", "%" + name + "%");
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске пользователей по имени: " + name, e);
        }
    }

    // Поиск пользователей старше определенного возраста
    public List<User> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "FROM User WHERE age > :age", User.class);
            query.setParameter("age", age);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске пользователей старше " + age, e);
        }
    }

    // Обновление email пользователя
    public void updateEmail(int userId, String newEmail) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setEmail(newEmail);
                session.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении email пользователя с ID: " + userId, e);
        }
    }
}