package com.najah.domain;

public class CarDriver extends Driver
{
    public CarDriver(String id, String name, boolean isAvailable, double distance, double estimatedTime)
    {
        super(id, name, isAvailable, distance, estimatedTime);
    }

    @Override
    public boolean canDeliver(Order order)
    {
        return super.canDeliver(order) && getDistance() <= 50.0;
    }
}