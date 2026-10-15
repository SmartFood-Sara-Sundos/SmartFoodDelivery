package com.najah.domain;
import java.util.List;

public class Restaurant
{
    private String id;
    private String name;
    private boolean isOpen;
    private List<MenuItem> menu;

    public Restaurant(String id, String name, boolean isOpen, List<MenuItem> menu)
    {
        this.id = id;
        this.name = name;
        this.isOpen = isOpen;
        this.menu = menu;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isOpen() { return isOpen; }
    public List<MenuItem> getMenu() { return menu; }
}