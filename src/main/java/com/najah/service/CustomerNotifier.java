package com.najah.service;

import java.util.logging.Logger;

public class CustomerNotifier implements OrderObserver{

    private static final Logger logger = Logger.getLogger(CustomerNotifier.class.getName());
    @Override
    public void update(String message){

        logger.info("[CUSTOMER NOTIFICATION] : "+ message);
    }
}
