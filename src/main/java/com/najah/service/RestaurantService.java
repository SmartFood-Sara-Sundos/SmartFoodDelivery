package com.najah.service;
import com.najah.domain.MenuItem;
import com.najah.domain.Restaurant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestaurantService
{
    // In-memory database storing restaurants with their ID as the key.
    private Map<String, Restaurant> restaurantDatabase = new HashMap<>();
    public void addRestaurant(Restaurant restaurant)
    {
        restaurantDatabase.put(restaurant.getId(), restaurant);
    }
    //return A List of currently open Restaurant objects.
    public List<Restaurant> getOpenRestaurants()
    {
        List<Restaurant> openRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurantDatabase.values())
        {
            if (restaurant.isOpen())
            {
                openRestaurants.add(restaurant);
            }
        }
        return openRestaurants;
    }
    public List<MenuItem> getAvailableMenu(String restaurantId)
    {
        Restaurant restaurant = restaurantDatabase.get(restaurantId);
        List<MenuItem> availableItems = new ArrayList<>();
        // if the restaurant exists, check its menu
        if (restaurant != null)
        {
            for (MenuItem item : restaurant.getMenu())
            {
                if (item.isAvailable())
                {
                    availableItems.add(item);
                }
            }
        }
        return availableItems;
    }
}
