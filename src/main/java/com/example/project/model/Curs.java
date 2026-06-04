package com.example.project.model;

public class Curs {
    private int uniqueIDcurs;
    private int price;
    private String title;
    private User owner;

    public Curs(int price, String title, User owner, int uniqueIDcurs){
        setOwner(owner);
        setPrice(price);
        setTitle(title);
        setUniqueIDcurs(uniqueIDcurs);
    }

    public int getUniqueIDcurs() {
        return uniqueIDcurs;
    }

    public void setUniqueIDcurs(int uniqueIDcurs) {
        this.uniqueIDcurs = uniqueIDcurs;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public User getOwner() {
        return owner;
    }
}
