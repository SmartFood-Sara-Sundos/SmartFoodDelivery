package com.najah.domain;

import com.najah.service.OrderObserver;

import java.util.ArrayList;
import java.util.List;

public class Order
{
    private final String orderId;
    private final User customer;
    private final Restaurant restaurant;
    private final List<OrderItem> items;
    private final double totalAmount;
    private String status; // Pending, Canceled, Preparing, Delivered
    private final String deliveryAddress;//edited
    private Driver assignedDriver;
    private transient List<OrderObserver> observers=new ArrayList<>();
    private boolean isPaid=false;


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

    public void setStatus(String status) { this.status = status;
        notifyObservers("Your order status has been updated to : "+status);//for us3.3
    }
    public Driver getAssignedDriver() { return assignedDriver; }

    public void setAssignedDriver(Driver assignedDriver) { this.assignedDriver = assignedDriver;
        notifyObservers("Driver "+ assignedDriver.getName()+" has been assigned to your order");
    }

    public void addObserver(OrderObserver observer){
        if(observers == null){
            observers = new ArrayList<>();
        }
        observers.add(observer);
    }
    private void notifyObservers(String message){
        if(observers != null){
           for(OrderObserver observer: observers){
               observer.update(message);
           }
        }

    }
    public boolean isPaid(){
        return isPaid;
    }
    public void setPaid(boolean paid){
        isPaid=paid;
    }


}