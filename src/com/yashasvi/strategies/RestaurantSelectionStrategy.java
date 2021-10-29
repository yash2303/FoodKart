package com.yashasvi.strategies;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.yashasvi.model.Restaurant;

public interface RestaurantSelectionStrategy {

    Optional<Restaurant> selectRestaurant(List<Restaurant> eligibleRestaurants, Map<String, Integer> orderItems);
}
