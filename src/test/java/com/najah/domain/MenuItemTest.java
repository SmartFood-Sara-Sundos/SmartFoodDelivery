package com.najah.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MenuItemTest {
    @Test
    void testMenuItemCreation() {
        MenuItem item = new MenuItem("M1", "Burger", 5.99, true);
        assertEquals("M1", item.getId());
        assertEquals("Burger", item.getName());
        assertEquals(5.99, item.getPrice());
        assertTrue(item.isAvailable());
    }
}