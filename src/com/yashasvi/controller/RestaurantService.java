package com.yashasvi.controller;

import java.util.Map;

import lombok.AllArgsConstructor;

import com.yashasvi.dao.InMemoryDAO;
import com.yashasvi.model.Restaurant;

@AllArgsConstructor
public class RestaurantService {
    private InMemoryDAO inMemoryDao;

    public void addRestaurant(String restaurantId, Map<String, Double> menu, Integer processingCapacity) {
        inMemoryDao.addRestaurant(restaurantId, menu, processingCapacity);
    }

    public void updateMenu(String restaurantId, Map<String, Double> menu) {
        inMemoryDao.updateMenu(restaurantId, menu);
    }

    public void printAllRestaurants() {
        Map<String, Restaurant> restaurantMap = inMemoryDao.getRestaurantMap();
        System.out.println(restaurantMap.values());
    }
}
