package com.najah.service;

import com.najah.domain.Driver;
import com.najah.domain.Order;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class DeliveryServiceTest
{
    @Test
    void testAssignNearestDriverStrategy()
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setDriverSelectionStrategy(new NearestDriverStrategy());

        Driver d1 = new Driver("D1", "Ahmad", true, 5.0, 15.0);
        Driver d2 = new Driver("D2", "Omar", true, 2.0, 20.0);
        List<Driver> drivers = Arrays.asList(d1, d2);

        Order order = new Order("O-100", null, null, Collections.emptyList(), null);

        boolean isAssigned = deliveryService.assignDriverToOrder(order, drivers);

        assertTrue(isAssigned);
        assertEquals("D2", order.getAssignedDriver().getId());
        assertFalse(d2.isAvailable());
    }

    @Test
    void testAssignFastestDriverStrategy()
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setDriverSelectionStrategy(new FastestDriverStrategy());

        Driver d1 = new Driver("D1", "Ahmad", true, 5.0, 10.0);
        Driver d2 = new Driver("D2", "Omar", true, 2.0, 20.0);
        List<Driver> drivers = Arrays.asList(d1, d2);

        Order order = new Order("O-101", null, null, Collections.emptyList(), null);

        boolean isAssigned = deliveryService.assignDriverToOrder(order, drivers);

        assertTrue(isAssigned);
        assertEquals("D1", order.getAssignedDriver().getId());
        assertFalse(d1.isAvailable());
    }

    @Test
    void testAssignmentRejectedIfNoDrivers()
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setDriverSelectionStrategy(new NearestDriverStrategy());

        Order order = new Order("O-102", null, null, Collections.emptyList(), null);
        boolean isAssigned = deliveryService.assignDriverToOrder(order, null);

        assertFalse(isAssigned);
        assertNull(order.getAssignedDriver());
    }
}