package com.najah.domain;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class CarDriverTest
{
    @Test
    void testCarSupportsLargeOrderAndLongDistance()
    {
        CarDriver driver = new CarDriver("C1", "Tariq", true, 30.0, 40.0);
        MenuItem item = new MenuItem("M1", "Pizza", 10.0, true);
        Order largeOrder = new Order("O-1", null, null, Arrays.asList(new OrderItem(item, 25)), "Nablus");

        assertTrue(driver.canDeliver(largeOrder));
    }

    @Test
    void testCarRejectsExcessiveDistanceOrNullOrder()
    {
        CarDriver driver = new CarDriver("C1", "Tariq", true, 60.0, 80.0);
        Order order = new Order("O-2", null, null, Collections.emptyList(), "Nablus");

        assertFalse(driver.canDeliver(order));
        assertFalse(driver.canDeliver(null));
    }
}