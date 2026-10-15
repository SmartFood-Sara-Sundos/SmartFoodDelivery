package com.najah.domain;

import java.util.List;

public class Order
{
    private String orderId;
    private User customer;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private double totalAmount;
    private String status; // Pending, Cancelled, Preparing, Delivered

    public Order(String orderId, User customer, Restaurant restaurant, List<OrderItem> items)
    {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        this.status = "Pending";
        this.totalAmount = calculateTotal();
    }


    private double calculateTotal()
    {
        double total = 0;
        for (OrderItem item : items)
        {
            total += item.getTotalPrice();
        }
        return total;
    }


    public String getOrderId() { return orderId; }
    public User getCustomer() { return customer; }
    public Restaurant getRestaurant() { return restaurant; }
    public List<OrderItem> getItems() { return items; }
    public double getTotalAmount() { return totalAmount; }
    public String getStatus() { return status; }


    public void setStatus(String status) { this.status = status; }
}