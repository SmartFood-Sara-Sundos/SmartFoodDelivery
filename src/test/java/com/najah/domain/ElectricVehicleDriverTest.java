package com.najah.domain;

import org.junit.jupiter.api.Test;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.*;

class ElectricVehicleDriverTest
{
    @Test
    void testElectricDriverWithSufficientBattery()
    {
        ElectricVehicleDriver driver = new ElectricVehicleDriver("E1", "Zaid", true, 10.0, 15.0, 50.0);
        Order order = new Order("O-1", null, null, Collections.emptyList(), "Nablus");

        assertEquals(50.0, driver.getBatteryPercentage());
        assertTrue(driver.canDeliver(order));
    }

    @Test
    void testElectricDriverRejectsLowBattery()
    {
        ElectricVehicleDriver driver = new ElectricVehicleDriver("E1", "Zaid", true, 2.0, 5.0, 15.0);
        Order order = new Order("O-2", null, null, Collections.emptyList(), "Nablus");

        assertFalse(driver.canDeliver(order));
    }

    @Test
    void testElectricDriverRejectsInsufficientRangeForDistance()
    {
        ElectricVehicleDriver driver = new ElectricVehicleDriver("E1", "Zaid", true, 20.0, 25.0, 30.0);
        Order order = new Order("O-3", null, null, Collections.emptyList(), "Nablus");

        assertFalse(driver.canDeliver(order));
        assertFalse(driver.canDeliver(null));
    }
}