package com.yashasvi.util;

import java.util.Map;

import com.yashasvi.model.Restaurant;

public class CostCalculator {
    public static Double getCost(Restaurant restaurant, Map<String, Integer> orderItems) {
        double totalCost = 0.0;
        for (Map.Entry<String, Integer> orderItem : orderItems.entrySet()) {
            String item = orderItem.getKey();
            Integer quantity = orderItem.getValue();
            Double itemPrice = restaurant.getMenu().get(item);
            double itemCost = itemPrice * quantity;
            totalCost += itemCost;
        }
        return totalCost;
    }
}
