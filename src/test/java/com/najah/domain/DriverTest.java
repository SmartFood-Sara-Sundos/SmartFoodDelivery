package com.najah.domain;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class DriverTest
{
    @Test
    void testBaseDriverRules()
    {
        Driver driver = new Driver("D1", "Ali", true, 5.0, 10.0);
        Order order = new Order("O-1", null, null, Collections.emptyList(), "Nablus");

        assertTrue(driver.canDeliver(order));
        assertFalse(driver.canDeliver(null));
        assertEquals(0, driver.getOrderTotalQuantity(null));
        assertEquals(10.0, driver.getEstimatedTime());
    }
}