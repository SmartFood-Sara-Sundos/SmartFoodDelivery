package com.najah.domain;

public class Driver
{
    private String id;
    private String name;
    private boolean isAvailable;
    private double distance;
    private double estimatedTime;

    public Driver(String id, String name, boolean isAvailable, double distance, double estimatedTime)
    {
        this.id = id;
        this.name = name;
        this.isAvailable = isAvailable;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
    }

    public boolean canDeliver(Order order)
    {
        return order != null;
    }

    protected int getOrderTotalQuantity(Order order)
    {
        if (order == null || order.getItems() == null)
        {
            return 0;
        }
        int totalQuantity = 0;
        for (OrderItem item : order.getItems())
        {
            totalQuantity += item.getQuantity();
        }
        return totalQuantity;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    public double getDistance() { return distance; }
    public double getEstimatedTime() { return estimatedTime; }
}