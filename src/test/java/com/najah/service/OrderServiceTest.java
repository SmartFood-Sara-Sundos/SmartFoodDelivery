package com.najah.service;

import com.najah.domain.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
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
        Restaurant rest = new Restaurant("R1", "KFC", true, Arrays.asList(burger,fries),5.0, List.of("Nablus"));

        Order order = new Order("O-001", customer, rest, items,"Nablus");

        Order placedOrder = orderService.placeOrder(order);

        assertEquals(12.0, placedOrder.getTotalAmount());
        assertEquals("Pending", placedOrder.getStatus());
    }

    @Test
    void testCancelPendingOrderSuccess()
    {
        OrderService orderService = new OrderService();
        MenuItem item = new MenuItem("M1","Burger",5.0,true);
        Restaurant rest = new Restaurant ("R1","KFC",true,Arrays.asList(item),2.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order order = new Order("O-002", customer, rest, Arrays.asList(new OrderItem(item,1)),"Nablus");
        orderService.placeOrder(order);
        boolean isCancelled = orderService.cancelOrder("O-002");

        assertTrue(isCancelled);
        assertEquals("Cancelled", order.getStatus());
    }

    @Test
    void testCancelOrderFailsIfNotPending()
    {
        OrderService orderService = new OrderService();
        MenuItem item = new MenuItem("M1","Burger",5.0,true);
        Restaurant rest = new Restaurant ("R1","KFC",true,Arrays.asList(item),2.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");
        Order order = new Order("O-003", customer, rest, Arrays.asList(new OrderItem(item,1)),"Nablus");
        orderService.placeOrder(order);

        order.setStatus("Preparing");

        boolean isCancelled = orderService.cancelOrder("O-003");

        assertFalse(isCancelled);
        assertEquals("Preparing", order.getStatus());
    }
    @Test
    void testPlaceOrder_EmptyOrder()
    {
        OrderService orderService = new OrderService();
        //MenuItem item = new MenuItem("M1","Burger",5.0,true);
        Restaurant rest = new Restaurant ("R1","KFC",true,null,2.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order emptyOrder = new Order("O-004", customer, rest, Collections.emptyList(),"Nablus");

        Exception e = assertThrows(IllegalArgumentException.class,() ->{
            orderService.placeOrder(emptyOrder);
    });
        assertTrue(e.getMessage().contains("Empty orders"));

    }
    @Test
    void testPlaceOrder_UnAvailableItem()
    {
        OrderService orderService = new OrderService();
        MenuItem item = new MenuItem("M1","Pizza",15.0,false);
        Restaurant rest = new Restaurant ("R1","Pizza Time",true,Arrays.asList(item),10.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order order = new Order("O-005", customer, rest, Arrays.asList(new OrderItem(item,1)),"Nablus");

        Exception e = assertThrows(IllegalArgumentException.class,() ->{
            orderService.placeOrder(order);
        });
        assertTrue(e.getMessage().contains("is unavailable"));

    }
    @Test
    void testPlaceOrder_BelowMinimumValue()
    {
        OrderService orderService = new OrderService();
        MenuItem cheapItem = new MenuItem("M1","Water",2.0,true);
        Restaurant rest = new Restaurant ("R1","Pizza Time",true,Arrays.asList(cheapItem),10.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order order = new Order("O-006", customer, rest, Arrays.asList(new OrderItem(cheapItem,1)),"Nablus");

        Exception e = assertThrows(IllegalArgumentException.class,() ->{
            orderService.placeOrder(order);
        });
        assertTrue(e.getMessage().contains("below the minimum"));

    }
    @Test
    void testPlaceOrder_OutsideDeliveryZone()
    {
        OrderService orderService = new OrderService();
        MenuItem meal = new MenuItem("M1","Pizza",15.0,true);
        Restaurant rest = new Restaurant ("R1","Pizza Time",true,Arrays.asList(meal),10.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order order = new Order("O-007", customer, rest, Arrays.asList(new OrderItem(meal,1)),"Haifa");

        Exception e = assertThrows(IllegalArgumentException.class,() ->{
            orderService.placeOrder(order);
        });
        assertTrue(e.getMessage().contains("does not deliver"));

    }
    @Test
    void testPlaceOrder_InvalidQuantity()
    {
        OrderService orderService = new OrderService();
        MenuItem dessert = new MenuItem("M1","Classic Cookie",7.0,true);
        Restaurant rest = new Restaurant ("R2","Vanilla",true,Arrays.asList(dessert),5.0,Arrays.asList("Nablus","Jenin"));
        User customer = new User("sara","123","Customer");

        Order order = new Order("O-008", customer, rest, Arrays.asList(new OrderItem(dessert,0)),"Haifa");

        Exception e = assertThrows(IllegalArgumentException.class,() ->{
            orderService.placeOrder(order);
        });
        assertTrue(e.getMessage().contains("Invalid quantity"));

    }
}