package com.najah.service;

import com.najah.domain.Order;

public class NormalPricingStrategy implements PricingStrategy
{
    @Override
    public double calculateFinalPrice(Order order)
    {
        return order.getTotalAmount();
    }
}