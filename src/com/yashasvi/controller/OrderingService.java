package com.yashasvi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;

import com.yashasvi.dao.DAO;
import com.yashasvi.exceptions.NoAvailableRestaurantsException;
import com.yashasvi.model.Order;
import com.yashasvi.model.OrderDetails;
import com.yashasvi.model.Restaurant;
import com.yashasvi.strategies.RestaurantSelectionStrategy;
import com.yashasvi.util.CostCalculator;

@AllArgsConstructor
public class OrderingService {
    private DAO dao;
    private RestaurantSelectionStrategy restaurantSelectionStrategy;

    public OrderDetails book(String customerId, Map<String, Integer> orderItems) {
        Map<String, Restaurant> restaurants = dao.getRestaurantMap();
        List<Restaurant> filteredRestaurants = filterRestaurants(restaurants, orderItems);
        Optional<Restaurant> restaurant = restaurantSelectionStrategy.selectRestaurant(filteredRestaurants, orderItems);
        if (restaurant.isEmpty()) {
            throw new NoAvailableRestaurantsException("No available restaurants");
        }
        Double cost = CostCalculator.getCost(restaurant.get(), orderItems);
        Order order = new Order(restaurant.get().getRestaurantId(), customerId, orderItems, cost);
        dao.placeOrder(order, restaurant.get());
        return new OrderDetails(restaurant.get().getRestaurantId(), order.getOrderId());
    }

    public void markAsDelivered(String orderId) {
        dao.markAsDelivered(orderId);
    }

    public void printAllOrders() {
        Map<String, Order> orderMap = dao.getOrderMap();
        System.out.println(orderMap.values());
    }

    private List<Restaurant> filterRestaurants(Map<String, Restaurant> restaurants, Map<String, Integer> orderItems) {
        Integer noOfItems = 0;
        for (Integer quantity : orderItems.values()) {
            noOfItems += quantity;
        }
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : restaurants.values()) {
            if (restaurant.getCapacityInUse().get() + noOfItems <= restaurant.getTotalCapacity()) {
                Set<String> itemsAvailable = restaurant.getMenu().keySet();
                boolean isEligible = true;
                for (String item : orderItems.keySet()) {
                    if (!itemsAvailable.contains(item)) {
                        isEligible = false;
                        break;
                    }
                }
                if (isEligible) {
                    filteredRestaurants.add(restaurant);
                }
            }
        }
        return filteredRestaurants;
    }
}
