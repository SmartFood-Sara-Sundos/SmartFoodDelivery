package com.najah.domain;

public class BicycleDriver extends Driver
{
    public BicycleDriver(String id, String name, boolean isAvailable, double distance, double estimatedTime)
    {
        super(id, name, isAvailable, distance, estimatedTime);
    }

    @Override
    public boolean canDeliver(Order order)
    {
        return super.canDeliver(order) && getDistance() <= 5.0 && getOrderTotalQuantity(order) <= 5;
    }
}