package com.example.project.service;

import com.example.project.model.Curs;
import com.example.project.model.User;
import com.example.project.repository.CursRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class CursService {

    // Вместо ArrayList - репозиторий
    private CursRepository cursRepository;

    // Конструктор для внедрения репозитория
    public CursService(SessionFactory sessionFactory) {
        this.cursRepository = new CursRepository(sessionFactory);
    }

    // Альтернативный конструктор для тестирования (dependency injection)
    public CursService(CursRepository cursRepository) {
        this.cursRepository = cursRepository;
    }

    //Сохранение курса через репозиторий
    public void addCourse(Curs curs) {
        cursRepository.save(curs);
        System.out.println("Курс добавлен: " + curs.getTitle());
    }

    //Получение курсов студента (владельца) через репозиторий
    public List<Curs> getCoursesByStudent(User user) {
        // Используем метод репозитория для поиска по владельцу
        return cursRepository.findByOwner(user);
    }

    //Получение всех курсов через репозиторий
    public List<Curs> getAllCourses() {
        return cursRepository.findAll();
    }

    //Печать всех курсов
    public void printAllCourses() {
        List<Curs> curses = cursRepository.findAll();
        if (curses.isEmpty()) {
            System.out.println("Список курсов пуст");
            return;
        }

        for (Curs c : curses) {
            System.out.println("Курс: " + c.getTitle() +
                    ", цена: " + c.getPrice() +
                    ", пользователь: " + (c.getOwner() != null ? c.getOwner().getName() : "не указан") +
                    ", идентификатор: " + c.getId());
        }
    }

    //Дополнительные методы, которые могут пригодиться

    // Поиск курса по ID
    public Curs getCourseById(int id) {
        return cursRepository.findById(id);
    }

    // Удаление курса по ID
    public void deleteCourse(int id) {
        cursRepository.deleteById(id);
        System.out.println("Курс с ID " + id + " удален");
    }

    // Обновление цены курса
    public void updateCoursePrice(int courseId, int newPrice) {
        cursRepository.updatePrice(courseId, newPrice);
        System.out.println("Цена курса с ID " + courseId + " обновлена до " + newPrice);
    }

    // Поиск курсов по цене
    public List<Curs> getCoursesCheaperThan(int maxPrice) {
        return cursRepository.findByPriceLessThan(maxPrice);
    }
}