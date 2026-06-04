package com.example.project.model;

public class User {
    private int uniqueIDuser;
    private String name;
    private int age;
    private String email;

    public User(String name, int age, String email, int uniqueIDuser){
        setName(name);
        setAge(age);
        setEmail(email);
        setUniqueIDuser(uniqueIDuser);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getUniqueIDuser() {
        return uniqueIDuser;
    }

    public void setUniqueIDuser(int uniqueID) {
        this.uniqueIDuser = uniqueID;
    }
}
