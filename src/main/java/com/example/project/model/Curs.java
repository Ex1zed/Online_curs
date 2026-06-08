package com.example.project.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Curs")  // Или "Сurs" - как у вас в БД
public class Curs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCurs")  // ← название колонки в БД
    private int id;

    @Column(name = "Price", nullable = false)
    private int price;

    @Column(name = "Title", nullable = false, length = 255)
    private String title;

    @ManyToOne
    @JoinColumn(name = "Owner", nullable = false)  // ← внешний ключ на User.idUser
    private User owner;

    public Curs() {
    }

    public Curs(int price, String title, User owner) {
        this.price = price;
        this.title = title;
        this.owner = owner;
    }

    // Getters и Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Curs{" +
                "id=" + id +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", owner=" + (owner != null ? owner.getName() : "null") +
                '}';
    }
}