package com.najah.service;
import com.najah.domain.Order;
import com.najah.domain.Driver;
import com.najah.service.OrderObserver;
import com.najah.domain.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class NotificationTest {

    @Test
    public void testNotification_AssignedDriver() {
        OrderObserver mockNotifier = Mockito.mock(OrderObserver.class);

        User customer = new User("Sondos", "135531", "Customer");
        Order order = new Order("0-001", customer, null, Collections.emptyList(), "Nablus");
        order.addObserver(mockNotifier);
        Driver driver = new Driver("ID1", "Omar", true, 5.0, 15.0);
        order.setAssignedDriver(driver);
        verify(mockNotifier,times(1)).update(contains("Omar has been assigned"));
    }
    @Test
    public void testNotification_OutForDelivery() {
        OrderObserver mockNotifier = Mockito.mock(OrderObserver.class);

       // User customer = new User("Sondos", "135531", "Customer");
        Order order = new Order("0-002", null, null, Collections.emptyList(), "Nablus");
        order.addObserver(mockNotifier);
        order.setStatus("Out for Delivery");
        verify(mockNotifier,times(1)).update(contains("Out for Delivery"));
    }
    @Test
    public void testNotification_Delivered() {
        OrderObserver mockNotifier = Mockito.mock(OrderObserver.class);
        Order order = new Order("0-003", null, null, Collections.emptyList(), "Nablus");
        order.addObserver(mockNotifier);
        order.setStatus("Delivered");
        verify(mockNotifier,times(1)).update(contains("Delivered"));
    }
}
