package com.example.project.service;

import com.example.project.model.User;
import com.example.project.repository.UserRepository;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserService {

    // ✅ Вместо ArrayList - репозиторий
    private UserRepository userRepository;

    // Конструктор для внедрения репозитория
    public UserService(SessionFactory sessionFactory) {
        this.userRepository = new UserRepository(sessionFactory);
    }

    // Альтернативный конструктор для тестирования (dependency injection)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ✅ Сохранение пользователя через репозиторий
    public void addStudent(User user) {
        userRepository.save(user);
        System.out.println("Пользователь добавлен: " + user.getName() + " (ID: " + user.getId() + ")");
    }

    // ✅ Получение всех пользователей через репозиторий
    public List<User> getAllStudents() {
        return userRepository.findAll();
    }

    // ✅ Печать всех пользователей
    public void printStudents() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("Список пользователей пуст");
            return;
        }

        for (User u : users) {
            System.out.println("Пользователь: " + u.getName() +
                    " (" + u.getEmail() + ")" +
                    " Возраст: " + u.getAge() +
                    " ID: " + u.getId());
        }
    }

    // ✅ Дополнительные методы, которые могут пригодиться

    // Поиск пользователя по ID
    public User getUserById(int id) {
        return userRepository.findById(id);
    }

    // Поиск пользователя по email
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Поиск пользователей по имени
    public List<User> getUsersByName(String name) {
        return userRepository.findByName(name);
    }

    // Удаление пользователя по ID
    public void deleteUser(int id) {
        userRepository.deleteById(id);
        System.out.println("Пользователь с ID " + id + " удален");
    }

    // Обновление email пользователя
    public void updateUserEmail(int userId, String newEmail) {
        userRepository.updateEmail(userId, newEmail);
        System.out.println("Email пользователя с ID " + userId + " обновлен на " + newEmail);
    }

    // Получение пользователей старше определенного возраста
    public List<User> getUsersOlderThan(int age) {
        return userRepository.findByAgeGreaterThan(age);
    }
}