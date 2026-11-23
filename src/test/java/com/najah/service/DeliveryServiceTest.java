package com.najah.service;

import com.najah.domain.*;
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
    void testAssignmentRejectedIfNoDriversOrNoStrategy()
    {
        DeliveryService deliveryService = new DeliveryService();
        Order order = new Order("O-102", null, null, Collections.emptyList(), null);

        assertFalse(deliveryService.assignDriverToOrder(order, Collections.emptyList()));

        deliveryService.setDriverSelectionStrategy(new NearestDriverStrategy());
        assertFalse(deliveryService.assignDriverToOrder(order, null));
        assertFalse(deliveryService.assignDriverToOrder(order, Collections.emptyList()));
        assertNull(order.getAssignedDriver());
    }

    @Test
    void testAssignmentFiltersIneligibleDriverTypesAndUnavailableDrivers()
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setDriverSelectionStrategy(new NearestDriverStrategy());

        MenuItem item = new MenuItem("M1", "Pizza", 10.0, true);
        Order largeOrder = new Order("O-103", null, null, Arrays.asList(new OrderItem(item, 15)), "Nablus");

        Driver busyCar = new CarDriver("C1", "Busy", false, 1.0, 5.0);
        Driver bike = new BicycleDriver("B1", "Bike", true, 2.0, 10.0);
        Driver eligibleCar = new CarDriver("C2", "Tariq", true, 12.0, 20.0);

        boolean isAssigned = deliveryService.assignDriverToOrder(largeOrder, Arrays.asList(busyCar, bike, eligibleCar));

        assertTrue(isAssigned);
        assertEquals("C2", largeOrder.getAssignedDriver().getId());
    }

    @Test
    void testAssignmentRejectedIfAllDriversIneligible()
    {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.setDriverSelectionStrategy(new NearestDriverStrategy());

        Order order = new Order("O-104", null, null, Collections.emptyList(), "Nablus");
        Driver lowBatteryEv = new ElectricVehicleDriver("E1", "Zaid", true, 5.0, 10.0, 10.0);
        Driver farBike = new BicycleDriver("B1", "Sami", true, 12.0, 30.0);

        boolean isAssigned = deliveryService.assignDriverToOrder(order, Arrays.asList(lowBatteryEv, farBike));

        assertFalse(isAssigned);
        assertNull(order.getAssignedDriver());
    }
}