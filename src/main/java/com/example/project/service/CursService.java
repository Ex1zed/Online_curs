package com.example.project.service;

import com.example.project.model.Curs;
import com.example.project.model.User;
import java.util.ArrayList;
import java.util.List;

public class CursService {
    private List<Curs> curses = new ArrayList<>();

    public void addCourse(Curs curs) {
        curses.add(curs);
    }

    public List<Curs> getCoursesByStudent(User user) {
        List<Curs> result = new ArrayList<>();
        for (Curs c : curses) {
            if (c.getOwner().equals(user)) {
                result.add(c);
            }
        }
        return result;
    }

    public void printAllCourses() {
        for (Curs c : curses) {
            System.out.println("Курс: " + c.getTitle() + ", цена: " + c.getPrice() +
                    ", пользователь: " + c.getOwner().getName());
        }
    }
}