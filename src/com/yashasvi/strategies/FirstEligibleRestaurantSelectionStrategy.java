package com.yashasvi.strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yashasvi.model.Restaurant;

public class FirstEligibleRestaurantSelectionStrategy implements RestaurantSelectionStrategy {
    @Override
    public Optional<Restaurant> selectRestaurant(
        List<Restaurant> eligibleRestaurants, Map<String, Integer> orderItems) {
        if (eligibleRestaurants.size() > 0)
            return Optional.of(eligibleRestaurants.get(0));
        return Optional.empty();
    }
}
