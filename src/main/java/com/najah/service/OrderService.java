package com.najah.service;

import com.najah.domain.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderService {

    // In-memory Database
    private Map<String, Order> orderDatabase = new HashMap<>();

    public Order placeOrder(Order order)
    {

        orderDatabase.put(order.getOrderId(), order);
        System.out.println("Order " + order.getOrderId() + " created successfully with status: " + order.getStatus());
        return order;
    }

    public boolean cancelOrder(String orderId)
    {
        Order order = orderDatabase.get(orderId);

        if (order == null) {
            System.out.println("Error: Order not found.");
            return false;
        }

        if (order.getStatus().equals("Pending"))
        {
            order.setStatus("Cancelled");
            System.out.println("Order " + orderId + " has been cancelled.");
            return true;
        }
        else
        {
            System.out.println("Error: Order cannot be cancelled because its status is " + order.getStatus());
            return false;
        }
    }
}