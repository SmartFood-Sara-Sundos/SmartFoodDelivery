package com.najah.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DomainTest {

    @Test
    void testMenuItem() {
        MenuItem item = new MenuItem("M1", "Burger", 5.99, true);
        assertEquals("M1", item.getId());
        assertEquals("Burger", item.getName());
        assertEquals(5.99, item.getPrice());
        assertTrue(item.isAvailable());
    }

    @Test
    void testRestaurant() {
        Restaurant rest = new Restaurant("R1", "KFC", true, null);
        assertEquals("R1", rest.getId());
        assertEquals("KFC", rest.getName());
        assertTrue(rest.isOpen());
        assertNull(rest.getMenu());
    }
}