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

    public String getId() { return id; }
    public String getName() { return name; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; } // عشان نغير حالته لـ Busy

    public double getDistance() { return distance; }
    public double getEstimatedTime() { return estimatedTime; }
}