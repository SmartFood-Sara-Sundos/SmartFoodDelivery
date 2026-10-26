package com.najah.service;

import com.najah.domain.Order;
import com.najah.domain.OrderItem;
import com.najah.domain.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class OrderService {

    // In-memory Database
    private Map<String, Order> orderDatabase = new HashMap<>();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OrderService.class.getName());


    private PricingService pricingService = new PricingService();

    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingService.setPricingStrategy(strategy);
    }


    public double getFinalReceipt(String orderId, com.najah.domain.Driver driver) {
        Order order = orderDatabase.get(orderId);
        if (order == null) {
            logger.warning("Error: Order not found.");
            return 0.0;
        }

        double finalPrice = pricingService.calculateTotalBill(order, driver);
        logger.info("Final Receipt for Order " + orderId + " is: " + finalPrice);

        return finalPrice;
    }

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
            logger.info("Order " + orderId + " has been cancelled.");
            return true;
        }
        else
        {
            logger.warning("Error: Order cannot be cancelled because its status is " + order.getStatus());
            return false;
        }
    }

    public void updateOrderStatus(Order order, String newStatus){
        String currentStatus = order.getStatus();

        if(currentStatus.equals("Delivered") || currentStatus.equals("Cancelled")){
            throw new IllegalArgumentException("Denied: Delivered or Cancelled orders cannot be modified");
        }
        boolean isValidTransition = false;
        switch(currentStatus){
            case "Pending":
                if (newStatus.equals("Confirmed") || newStatus.equals("Cancelled")){
                    isValidTransition = true;}break;
            case "Confirmed":
                if(newStatus.equals("Preparing")){
                    isValidTransition = true;}
                    break;

            case "Preparing":
                if(newStatus.equals("Ready")){
                    isValidTransition = true;}
                    break;

            case "Ready":
                if(newStatus.equals("Out for Delivery")){
                    isValidTransition = true;}
                    break;

            case "Out for Delivery":
                if(newStatus.equals("Delivered")){
                    isValidTransition = true;}
                    break;
                }
                if(isValidTransition){
                    order.setStatus(newStatus);
                }else{
                    throw new IllegalArgumentException("Denied: Invalid state transition from "+currentStatus+" to "+newStatus);
                }

        }



    public void payForOrder(Order order, double amountPaid, PaymentService paymentService){
        if(order.isPaid()){
            throw new IllegalArgumentException("Denied: Order is already paid");
        }
        if(paymentService.processPayment(amountPaid)) {
            order.setPaid(true);
        }else{
            throw new IllegalArgumentException("Denied: Payment processing failed");
        }
    }
}