package com.najah.domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class MotorcycleDriverTest
{
    @Test
    void testMotorcycleCanDeliverMediumOrder()
    {
        MotorcycleDriver driver = new MotorcycleDriver("M1", "Rami", true, 12.0, 20.0);
        MenuItem item = new MenuItem("M1", "Meal", 8.0, true);
        Order order = new Order("O-1", null, null, Arrays.asList(new OrderItem(item, 8)), "Nablus");

        assertTrue(driver.canDeliver(order));
    }

    @Test
    void testMotorcycleRejectsTooFarDistance()
    {
        MotorcycleDriver driver = new MotorcycleDriver("M1", "Rami", true, 18.0, 30.0);
        Order order = new Order("O-2", null, null, Collections.emptyList(), "Nablus");

        assertFalse(driver.canDeliver(order));
    }

    @Test
    void testMotorcycleRejectsVeryLargeOrder()
    {
        MotorcycleDriver driver = new MotorcycleDriver("M1", "Rami", true, 10.0, 15.0);
        MenuItem item = new MenuItem("M1", "Meal", 8.0, true);
        Order largeOrder = new Order("O-3", null, null, Arrays.asList(new OrderItem(item, 12)), "Nablus");

        assertFalse(driver.canDeliver(largeOrder));
        assertFalse(driver.canDeliver(null));
    }
}