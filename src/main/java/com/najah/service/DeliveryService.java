package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.Order;
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
        if (driverSelectionStrategy == null)
        {
            return false;
        }

        Driver selectedDriver = driverSelectionStrategy.selectDriver(availableDrivers);

        if (selectedDriver != null)
        {
            selectedDriver.setAvailable(false);
            order.setAssignedDriver(selectedDriver);
            return true;
        }

        return false;
    }
}