package com.yashasvi.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

import com.yashasvi.exceptions.InvalidRequestException;
import com.yashasvi.model.Order;
import com.yashasvi.model.OrderStatus;
import com.yashasvi.model.Restaurant;

@Getter
public class DAO {
    private final Map<String, Restaurant> restaurantMap;
    private final Map<String, Order> orderMap;

    public DAO() {
        this.restaurantMap = new HashMap<>();
        this.orderMap = new HashMap<>();
    }

    public void addRestaurant(String restaurantId, Map<String, Double> menu, Integer processingCapacity) {
        if (restaurantMap.containsKey(restaurantId)) {
            throw new InvalidRequestException("Restaurant already exists");
        }
        Restaurant restaurant = new Restaurant(restaurantId, menu, processingCapacity);
        restaurantMap.put(restaurantId, restaurant);
    }

    public void updateMenu(String restaurantId, Map<String, Double> menu) {
        Restaurant restaurant = restaurantMap.get(restaurantId);
        if (restaurant == null) {
            throw new InvalidRequestException("Restaurant doesn't exist");
        }
        for (Map.Entry<String, Double> item : menu.entrySet()) {
            restaurant.getMenu().put(item.getKey(), item.getValue());
        }
    }

    public void markAsDelivered(String orderId) {
        Order order = orderMap.get(orderId);
        if (order == null) {
            throw new InvalidRequestException("Order doesn't exist");
        }
        order.setOrderStatus(OrderStatus.DELIVERED);
        order.setDeliveredAt(new Date());
        Restaurant restaurant = restaurantMap.get(order.getRestaurantId());
        Map<String, Integer> orderItems = order.getOrderItems();
        Integer noOfItems = 0;
        for (Integer quantity : orderItems.values()) {
            noOfItems += quantity;
        }
        restaurant.setCapacityInUse(restaurant.getCapacityInUse().get() - noOfItems);
    }

    // Take current capacity in use as input and use AtomicInteger.compareAndSet while updating capacity in use
    public void placeOrder(Order order, Restaurant restaurant) {
        Integer noOfItems = 0;
        for (Integer quantity : order.getOrderItems().values()) {
            noOfItems += quantity;
        }
        restaurant.setCapacityInUse(restaurant.getCapacityInUse().get() + noOfItems);
        restaurant.getOrders().add(order);
        orderMap.put(order.getOrderId(), order);
    }
}
