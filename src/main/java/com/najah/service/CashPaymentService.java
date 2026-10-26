package com.najah.service;

public class CashPaymentService implements PaymentService{

    @Override
    public boolean processPayment(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Denied: Invalid payment amount");
        }
        return true;
    }
}
