package com.najah.domain;

public class MotorcycleDriver extends Driver
{
    public MotorcycleDriver(String id, String name, boolean isAvailable, double distance, double estimatedTime)
    {
        super(id, name, isAvailable, distance, estimatedTime);
    }

    @Override
    public boolean canDeliver(Order order)
    {
        return super.canDeliver(order) && getDistance() <= 15.0 && getOrderTotalQuantity(order) <= 10;
    }
}