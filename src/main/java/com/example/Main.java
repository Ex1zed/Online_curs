package com.example;

import com.example.project.model.User;
import com.example.project.model.Curs;
import com.example.project.service.UserService;
import com.example.project.service.CursService;

public class Main {
    public static void main(String[] args) {

        // 1. Создаём студентов
        User Igor = new User("Игорь",18,"igor0206@mail.ru",1);
        User Marat = new User("Марат",17, "maratkarbekov@yandex.ru",2);
        User Stas = new User("Стас", 17, "staslemesev67@mail.ru",3);

        // 2. Сервисы
        UserService userService = new UserService();
        CursService cursService = new CursService();

        // 3. Добавляем студентов в систему
        userService.addStudent(Igor);
        userService.addStudent(Marat);
        userService.addStudent(Stas);

        // 4. Создаём курсы и связываем их со студентами
        Curs javaCourse = new Curs(5000, "Java для начинающих",Marat,1);
        Curs pythonCourse = new Curs(6000, "Python с нуля", Igor,2);
        Curs springCourse = new Curs(7000, "Spring Boot", Stas,3);

        // 5. Добавляем курсы
        cursService.addCourse(javaCourse);
        cursService.addCourse(pythonCourse);
        cursService.addCourse(springCourse);

        // 6. Вывод всех студентов
        System.out.println("=== Список студентов ===");
        userService.printStudents();

        // 7. Вывод всех курсов
        System.out.println("\n=== Все курсы ===");
        cursService.printAllCourses();

        // 8. Курсы конкретного студента
        System.out.println("\n=== Курсы Стаса ===");
        for (Curs c : cursService.getCoursesByStudent(Stas)) {
            System.out.println("- " + c.getTitle() + " (" + c.getPrice() + " руб.)");
        }
    }
}