package com.najah.service;

import com.najah.domain.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest
{
    @Test
    void testPlaceOrderCalculatesTotalCorrectly()
    {
        OrderService orderService = new OrderService();
        MenuItem burger = new MenuItem("M1", "Burger", 5.0, true);
        MenuItem fries = new MenuItem("M2", "Fries", 2.0, true);

        OrderItem item1 = new OrderItem(burger, 2);
        OrderItem item2 = new OrderItem(fries, 1);
        List<OrderItem> items = Arrays.asList(item1, item2);

        User customer = new User("sara", "123", "Customer");
        Restaurant rest = new Restaurant("R1", "KFC", true, null);

        Order order = new Order("O-001", customer, rest, items);

        Order placedOrder = orderService.placeOrder(order);

        assertEquals(12.0, placedOrder.getTotalAmount());
        assertEquals("Pending", placedOrder.getStatus());
    }

    @Test
    void testCancelPendingOrderSuccess()
    {
        OrderService orderService = new OrderService();
        Order order = new Order("O-002", null, null, Arrays.asList());
        orderService.placeOrder(order);
        boolean isCancelled = orderService.cancelOrder("O-002");

        assertTrue(isCancelled);
        assertEquals("Cancelled", order.getStatus());
    }

    @Test
    void testCancelOrderFailsIfNotPending()
    {
        OrderService orderService = new OrderService();
        Order order = new Order("O-003", null, null, Arrays.asList());
        orderService.placeOrder(order);

        order.setStatus("Preparing");

        boolean isCancelled = orderService.cancelOrder("O-003");

        assertFalse(isCancelled);
        assertEquals("Preparing", order.getStatus());
    }
}