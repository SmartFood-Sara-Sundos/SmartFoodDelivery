package com.najah.service;
import com.najah.domain.MenuItem;
import com.najah.domain.Restaurant;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class RestaurantServiceTest {


    @Test
    public void testGetOpenRestaurants() {
        // 1. Setup
        RestaurantService service = new RestaurantService();
        Restaurant openRest = new Restaurant("R1", "Open Burger", true, null);
        Restaurant closedRest = new Restaurant("R2", "Closed Pizza", false, null);

        service.addRestaurant(openRest);
        service.addRestaurant(closedRest);
        List<Restaurant> result = service.getOpenRestaurants();

        assertEquals(1, result.size(), "Should return only 1 open restaurant");
        assertEquals("Open Burger", result.get(0).getName());
    }

    @Test
    public void testGetAvailableMenu() {
        RestaurantService service = new RestaurantService();
        MenuItem availableItem = new MenuItem("M1", "Fries", 2.99, true);
        MenuItem unavailableItem = new MenuItem("M2", "Ice Cream", 1.99, false);

        List<MenuItem> menu = Arrays.asList(availableItem, unavailableItem);
        Restaurant rest = new Restaurant("R1", "Snack Bar", true, menu);

        service.addRestaurant(rest);

        List<MenuItem> result = service.getAvailableMenu("R1");

        assertEquals(1, result.size(), "Should return only 1 available menu item");
        assertEquals("Fries", result.get(0).getName());
    }
}