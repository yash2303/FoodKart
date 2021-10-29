package com.yashasvi.controller;

import java.util.Map;

import lombok.AllArgsConstructor;

import com.yashasvi.dao.DAO;
import com.yashasvi.model.Restaurant;

@AllArgsConstructor
public class RestaurantService {
    private DAO dao;

    public void addRestaurant(String restaurantId, Map<String, Double> menu, Integer processingCapacity) {
        dao.addRestaurant(restaurantId, menu, processingCapacity);
    }

    public void updateMenu(String restaurantId, Map<String, Double> menu) {
        dao.updateMenu(restaurantId, menu);
    }

    public void printAllRestaurants() {
        Map<String, Restaurant> restaurantMap = dao.getRestaurantMap();
        System.out.println(restaurantMap.values());
    }
}
