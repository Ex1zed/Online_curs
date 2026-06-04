package com.example.project.service;
import com.example.project.model.User;
import java.util.ArrayList;
import java.util.List;
public class UserService {
    private List<User> users = new ArrayList<>();

    public void addStudent(User user) {
        users.add(user);
    }
    public List<User> getAllStudents() {
        return users;
    }
    public void printStudents() {
        for(User u : users) {
            System.out.println("Пользователь: " + u.getName() + " (" + u.getEmail() + ")" + " Возраст: "+u.getAge());
        }
    }
}
