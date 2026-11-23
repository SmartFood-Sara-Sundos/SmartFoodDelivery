package com.najah.domain;

public class ElectricVehicleDriver extends Driver
{
    private double batteryPercentage;

    public ElectricVehicleDriver(String id, String name, boolean isAvailable, double distance, double estimatedTime, double batteryPercentage)
    {
        super(id, name, isAvailable, distance, estimatedTime);
        this.batteryPercentage = batteryPercentage;
    }

    public double getBatteryPercentage()
    {
        return batteryPercentage;
    }

    @Override
    public boolean canDeliver(Order order)
    {
        return super.canDeliver(order) && batteryPercentage >= 20.0 && batteryPercentage >= (getDistance() * 2.0);
    }
}