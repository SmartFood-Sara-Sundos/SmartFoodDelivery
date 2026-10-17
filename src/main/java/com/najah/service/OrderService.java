package com.najah.service;

import com.najah.domain.Order;
import com.najah.domain.OrderItem;
import com.najah.domain.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class OrderService {

    // In-memory Database
    private Map<String, Order> orderDatabase = new HashMap<>();

    public Order placeOrder(Order order) {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new IllegalArgumentException("Rejected:Empty orders are not allowed");
        }

        // 1. فحص الوجبات (جوا اللوب)
        for (OrderItem item : order.getItems()) {
            if (item.getQuantity() <= 0) {
                throw new IllegalArgumentException("Rejected:Invalid quantity");
            }
            if (!item.getMenuItem().isAvailable()) {
                throw new IllegalArgumentException("Rejected:Item " + item.getMenuItem().getName() + " is unavailable");
            }
        }

        Restaurant restaurant = order.getRestaurant();

        if (order.getDeliveryAddress() == null || order.getDeliveryAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Rejected:Invalid delivery address");
        }
        if (!restaurant.getDeliveryZones().contains(order.getDeliveryAddress())) {
            throw new IllegalArgumentException("Rejected:Restaurant does not deliver to this address");
        }
        if (order.getTotalAmount() < restaurant.getMinimumOrderValue()) {
            throw new IllegalArgumentException("Rejected:Order total is below the minimum value");
        }


        orderDatabase.put(order.getOrderId(), order);
        return order;
    }

    public boolean cancelOrder(String orderId)
    {
        Order order = orderDatabase.get(orderId);

        if (order == null) {
            return false;
        }

        if (order.getStatus().equals("Pending"))
        {
            order.setStatus("Cancelled");
            //System.out.println("Order " + orderId + " has been cancelled.");
            return true;
        }
        else
        {
            //System.out.println("Error: Order cannot be cancelled because its status is " + order.getStatus());
            return false;
        }
    }
}