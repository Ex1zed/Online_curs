package com.example;

import com.example.project.model.Curs;
import com.example.project.model.User;
import com.example.project.service.CursService;
import com.example.project.service.UserService;
import com.example.project.util.HibernateUtil;
import org.hibernate.SessionFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Инициализация SessionFactory
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        // Создание сервисов с внедрением SessionFactory
        UserService userService = new UserService(sessionFactory);
        CursService cursService = new CursService(sessionFactory);

        // ========== РАБОТА С ПОЛЬЗОВАТЕЛЯМИ ==========
        System.out.println("=== РАБОТА С ПОЛЬЗОВАТЕЛЯМИ ===\n");

        // Добавление пользователей
        User user1 = new User("Иван Петров", 25, "ivan@example.com");
        User user2 = new User("Мария Сидорова", 30, "maria@example.com");

        userService.addStudent(user1);
        userService.addStudent(user2);

        // Вывод всех пользователей
        System.out.println("\nСписок всех пользователей:");
        userService.printStudents();

        // Поиск пользователя по email
        System.out.println("\nПоиск пользователя по email 'ivan@example.com':");
        User foundUser = userService.getUserByEmail("ivan@example.com");
        if (foundUser != null) {
            System.out.println("Найден: " + foundUser.getName());
        }

        // ========== РАБОТА С КУРСАМИ ==========
        System.out.println("\n=== РАБОТА С КУРСАМИ ===\n");

        // Добавление курсов
        // Сначала получим пользователей из БД (с актуальными ID)
        User dbUser1 = userService.getUserByEmail("ivan@example.com");
        User dbUser2 = userService.getUserByEmail("maria@example.com");

        Curs curs1 = new Curs(15000, "Java для начинающих", dbUser1);
        Curs curs2 = new Curs(25000, "Spring Framework", dbUser1);
        Curs curs3 = new Curs(20000, "Базы данных SQL", dbUser2);

        cursService.addCourse(curs1);
        cursService.addCourse(curs2);
        cursService.addCourse(curs3);

        // Вывод всех курсов
        System.out.println("\nСписок всех курсов:");
        cursService.printAllCourses();

        // Поиск курсов конкретного студента
        System.out.println("\nКурсы пользователя " + dbUser1.getName() + ":");
        List<Curs> userCourses = cursService.getCoursesByStudent(dbUser1);
        for (Curs curs : userCourses) {
            System.out.println("  - " + curs.getTitle() + " (ID: " + curs.getId() + ")");
        }

        // Обновление цены курса
        System.out.println("\nОбновление цены курса...");
        if (!userCourses.isEmpty()) {
            cursService.updateCoursePrice(userCourses.get(0).getId(), 18000);
        }

        // Вывод обновленного списка
        System.out.println("\nОбновленный список курсов:");
        cursService.printAllCourses();

        // Поиск дешевых курсов
        System.out.println("\nКурсы дешевле 20000:");
        List<Curs> cheapCourses = cursService.getCoursesCheaperThan(20000);
        for (Curs curs : cheapCourses) {
            System.out.println("  - " + curs.getTitle() + " (" + curs.getPrice() + " руб.)");
        }

        // Закрытие SessionFactory
        sessionFactory.close();
    }
}