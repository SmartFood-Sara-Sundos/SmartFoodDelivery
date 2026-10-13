package com.najah.domain;

import java.util.Objects;
public class MenuItem {
    private String id;
    private String name;
    private double price;
    private boolean isAvailable;

    public MenuItem(String id, String name, double price, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.isAvailable = isAvailable;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return isAvailable; }


}