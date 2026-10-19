package com.najah.service;

import com.najah.domain.Order;

public class PromotionalPricingStrategy implements PricingStrategy
{
    @Override
    public double calculateFinalPrice(Order order)
    {

        return order.getTotalAmount() * 0.85;
    }
}