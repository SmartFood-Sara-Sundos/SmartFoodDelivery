package com.najah.domain;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest
{
    @Test
    void testRestaurantCreation()
    {
        Restaurant rest = new Restaurant("R1", "KFC", true, null);
        assertEquals("R1", rest.getId());
        assertEquals("KFC", rest.getName());
        assertTrue(rest.isOpen());
        assertNull(rest.getMenu());
    }
}