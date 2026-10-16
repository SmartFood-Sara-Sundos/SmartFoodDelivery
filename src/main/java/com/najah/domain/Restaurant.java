package com.najah.domain;
import java.util.List;

public class Restaurant
{
    private String id;
    private String name;
    private boolean isOpen;
    private List<MenuItem> menu;
    private double minimumOrderValue;//new
    private List<String> deliveryZones;//new

    public Restaurant(String id, String name, boolean isOpen, List<MenuItem> menu,
                      double minimumOrderValue, List<String> deliveryZones)
    {
        this.id = id;
        this.name = name;
        this.isOpen = isOpen;
        this.menu = menu;
        this.minimumOrderValue= minimumOrderValue;
        this.deliveryZones=deliveryZones;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isOpen() { return isOpen; }
    public List<MenuItem> getMenu() { return menu; }
    public double getMinimumOrderValue(){return minimumOrderValue;}
    public List<String>getDeliveryZones(){return deliveryZones;}
}