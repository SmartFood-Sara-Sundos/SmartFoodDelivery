package com.najah.domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class BicycleDriverTest
{
    @Test
    void testBicycleCanDeliverValidOrder()
    {
        BicycleDriver driver = new BicycleDriver("B1", "Sami", true, 3.0, 12.0);
        MenuItem item = new MenuItem("M1", "Sandwich", 4.0, true);
        Order order = new Order("O-1", null, null, Arrays.asList(new OrderItem(item, 2)), "Nablus");

        assertTrue(driver.canDeliver(order));
    }

    @Test
    void testBicycleRejectsLongDistance()
    {
        BicycleDriver driver = new BicycleDriver("B1", "Sami", true, 6.5, 25.0);
        Order order = new Order("O-2", null, null, Collections.emptyList(), "Nablus");

        assertFalse(driver.canDeliver(order));
    }

    @Test
    void testBicycleRejectsLargeOrder()
    {
        BicycleDriver driver = new BicycleDriver("B1", "Sami", true, 2.0, 10.0);
        MenuItem item = new MenuItem("M1", "Burger", 5.0, true);
        Order largeOrder = new Order("O-3", null, null, Arrays.asList(new OrderItem(item, 6)), "Nablus");

        assertFalse(driver.canDeliver(largeOrder));
        assertFalse(driver.canDeliver(null));
    }
}