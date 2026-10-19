package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.Order;

public class PricingService
{
    private PricingStrategy pricingStrategy;

    public void setPricingStrategy(PricingStrategy pricingStrategy)
    {
        this.pricingStrategy = pricingStrategy;
    }


    public double calculateDeliveryCost(Driver driver)
    {
        if (driver == null)
        {
            return 0.0;
        }
        return driver.getDistance() * 1.5;
    }


    public double calculateTotalBill(Order order, Driver driver)
    {
        double foodPrice = order.getTotalAmount();


        if (pricingStrategy != null)
        {
            foodPrice = pricingStrategy.calculateFinalPrice(order);
        }

        double deliveryCost = calculateDeliveryCost(driver);

        return foodPrice + deliveryCost;
    }
}