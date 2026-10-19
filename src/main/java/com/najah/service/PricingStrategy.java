package com.najah.service;

import com.najah.domain.Order;

public interface PricingStrategy
{
    double calculateFinalPrice(Order order);
}