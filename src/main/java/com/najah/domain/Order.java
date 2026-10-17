package com.najah.domain;

import java.util.List;

public class Order
{
    private String orderId;
    private User customer;
    private Restaurant restaurant;
    private List<OrderItem> items;
    private double totalAmount;
    private String status; // Pending, Canceled, Preparing, Delivered
    private String deliveryAddress;//edited
    private Driver assignedDriver;

    public Order(String orderId, User customer, Restaurant restaurant, List<OrderItem> items,String deliveryAddress)
    {
        this.orderId = orderId;
        this.customer = customer;
        this.restaurant = restaurant;
        this.items = items;
        this.status = "Pending";
        this.totalAmount = calculateTotal();
        this.deliveryAddress=deliveryAddress;
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
    public String getDeliveryAddress(){ return deliveryAddress; }

    public void setStatus(String status) { this.status = status; }
    public Driver getAssignedDriver() { return assignedDriver; }

    public void setAssignedDriver(Driver assignedDriver) { this.assignedDriver = assignedDriver; }
}