package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.Order;
import java.util.ArrayList;
import java.util.List;

public class DeliveryService
{
    private DriverSelectionStrategy driverSelectionStrategy;

    public void setDriverSelectionStrategy(DriverSelectionStrategy strategy)
    {
        this.driverSelectionStrategy = strategy;
    }

    public boolean assignDriverToOrder(Order order, List<Driver> availableDrivers)
    {
        if (driverSelectionStrategy == null || availableDrivers == null || availableDrivers.isEmpty())
        {
            return false;
        }

        List<Driver> eligibleDrivers = new ArrayList<>();
        for (Driver driver : availableDrivers)
        {
            if (driver.isAvailable() && driver.canDeliver(order))
            {
                eligibleDrivers.add(driver);
            }
        }

        if (eligibleDrivers.isEmpty())
        {
            return false;
        }

        Driver selectedDriver = driverSelectionStrategy.selectDriver(eligibleDrivers);

        if (selectedDriver != null)
        {
            selectedDriver.setAvailable(false);
            order.setAssignedDriver(selectedDriver);
            return true;
        }

        return false;
    }
}