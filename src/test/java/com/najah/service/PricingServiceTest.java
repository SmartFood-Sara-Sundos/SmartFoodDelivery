package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.MenuItem;
import com.najah.domain.Order;
import com.najah.domain.OrderItem;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class PricingServiceTest
{
    @Test
    void testCalculateDeliveryCost()
    {
        PricingService pricingService = new PricingService();

        Driver driver = new Driver("D1", "Omar", true, 10.0, 20.0);


        double cost = pricingService.calculateDeliveryCost(driver);
        assertEquals(15.0, cost);


        assertEquals(0.0, pricingService.calculateDeliveryCost(null));
    }

    @Test
    void testNormalPricingStrategy()
    {
        PricingService pricingService = new PricingService();
        pricingService.setPricingStrategy(new NormalPricingStrategy());

        Order order = createMockOrderWithTotal100();
        Driver driver = new Driver("D1", "Omar", true, 10.0, 20.0); // 15 دينار توصيل


        double totalBill = pricingService.calculateTotalBill(order, driver);
        assertEquals(115.0, totalBill);
    }

    @Test
    void testPeakHourPricingStrategy()
    {
        PricingService pricingService = new PricingService();
        pricingService.setPricingStrategy(new PeakHourPricingStrategy());

        Order order = createMockOrderWithTotal100();
        Driver driver = new Driver("D1", "Omar", true, 10.0, 20.0); // 15 دينار توصيل


        double totalBill = pricingService.calculateTotalBill(order, driver);
        assertEquals(135.0, totalBill);
    }

    @Test
    void testPromotionalPricingStrategy()
    {
        PricingService pricingService = new PricingService();
        pricingService.setPricingStrategy(new PromotionalPricingStrategy());

        Order order = createMockOrderWithTotal100();
        Driver driver = new Driver("D1", "Omar", true, 10.0, 20.0); // 15 دينار توصيل


        double totalBill = pricingService.calculateTotalBill(order, driver);
        assertEquals(100.0, totalBill);
    }


    private Order createMockOrderWithTotal100()
    {
        MenuItem item = new MenuItem("M1", "Family Meal", 100.0, true);
        OrderItem orderItem = new OrderItem(item, 1);
        return new Order("O-999", null, null, Arrays.asList(orderItem), "Amman");
    }
}