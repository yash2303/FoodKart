package com.yashasvi.strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yashasvi.model.Restaurant;
import com.yashasvi.util.CostCalculator;

public class LowestOrderAmountStrategy implements RestaurantSelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(
        List<Restaurant> eligibleRestaurants,
        Map<String, Integer> orderItems) {
        return eligibleRestaurants.stream()
            .min((restaurant1, restaurant2) -> (int) (CostCalculator.getCost(restaurant1, orderItems)
                - CostCalculator.getCost(restaurant2, orderItems)));
    }
}
