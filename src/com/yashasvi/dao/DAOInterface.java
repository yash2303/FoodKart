package com.yashasvi.dao;

import java.util.Map;

import com.yashasvi.model.Order;
import com.yashasvi.model.Restaurant;

public interface DAOInterface {
    void addRestaurant(String restaurantId, Map<String, Double> menu, Integer processingCapacity);

    void updateMenu(String restaurantId, Map<String, Double> menu);

    void markAsDelivered(String orderId);

    void placeOrder(Order order, Restaurant restaurant);
}
