package com.example.project.repository;

import com.example.project.model.Curs;
import com.example.project.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CursRepository extends BaseRepository<Curs, Integer> {

    public CursRepository(SessionFactory sessionFactory) {
        super(sessionFactory, Curs.class);
    }

    // Дополнительные методы, специфичные для Curs

    // Поиск курсов по названию
    public List<Curs> findByTitle(String title) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curs> query = session.createQuery(
                    "FROM Curs WHERE title LIKE :title", Curs.class);
            query.setParameter("title", "%" + title + "%");
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске курсов по названию: " + title, e);
        }
    }

    // Поиск курсов по цене (дешевле указанной)
    public List<Curs> findByPriceLessThan(int maxPrice) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curs> query = session.createQuery(
                    "FROM Curs WHERE price < :maxPrice", Curs.class);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске курсов дешевле " + maxPrice, e);
        }
    }

    // Поиск курсов по цене (в диапазоне)
    public List<Curs> findByPriceBetween(int minPrice, int maxPrice) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curs> query = session.createQuery(
                    "FROM Curs WHERE price BETWEEN :minPrice AND :maxPrice", Curs.class);
            query.setParameter("minPrice", minPrice);
            query.setParameter("maxPrice", maxPrice);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске курсов в диапазоне цен от " +
                    minPrice + " до " + maxPrice, e);
        }
    }

    // Поиск курсов конкретного пользователя
    public List<Curs> findByOwner(User owner) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curs> query = session.createQuery(
                    "FROM Curs WHERE owner = :owner", Curs.class);
            query.setParameter("owner", owner);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске курсов пользователя: " + owner.getName(), e);
        }
    }

    // Поиск курсов по ID пользователя
    public List<Curs> findByOwnerId(int ownerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<Curs> query = session.createQuery(
                    "FROM Curs WHERE owner.id = :ownerId", Curs.class);
            query.setParameter("ownerId", ownerId);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при поиске курсов по ID пользователя: " + ownerId, e);
        }
    }

    // Обновление цены курса
    public void updatePrice(int cursId, int newPrice) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Curs curs = session.get(Curs.class, cursId);
            if (curs != null) {
                curs.setPrice(newPrice);
                session.merge(curs);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Ошибка при обновлении цены курса с ID: " + cursId, e);
        }
    }
}