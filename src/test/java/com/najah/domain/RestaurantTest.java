package com.najah.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest
{
    @Test
    void testRestaurantCreation()
    {
        Restaurant rest = new Restaurant("R1", "KFC", true, null,5.0, Arrays.asList("Nablus","Jaffa"));
        assertEquals("R1", rest.getId());
        assertEquals("KFC", rest.getName());
        assertTrue(rest.isOpen());
        assertNull(rest.getMenu());
        assertEquals(5.0, rest.getMinimumOrderValue());
        assertEquals(Arrays.asList("Nablus","Jaffa"), rest.getDeliveryZones());
    }
}