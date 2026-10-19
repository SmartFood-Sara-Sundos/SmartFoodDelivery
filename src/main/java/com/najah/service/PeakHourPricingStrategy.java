package com.najah.service;

import com.najah.domain.Order;

public class PeakHourPricingStrategy implements PricingStrategy
{
    @Override
    public double calculateFinalPrice(Order order)
    {

        return order.getTotalAmount() * 1.20;
    }
}