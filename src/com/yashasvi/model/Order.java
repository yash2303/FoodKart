package com.yashasvi.model;

import java.util.Date;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private static int orderNumber = 0;
    private String orderId;
    private String restaurantId;
    private String user;
    private Map<String, Integer> orderItems; // Item, Quantity
    private OrderStatus orderStatus;
    private Double cost;
    private Date placedAt;
    private Date deliveredAt;

    public Order(String restaurantId, String user, Map<String, Integer> orderItems, Double cost) {
        this.restaurantId = restaurantId;
        this.user = user;
        this.orderItems = orderItems;
        this.cost = cost;
        this.orderId = "order" + (++orderNumber);
        this.orderStatus = OrderStatus.PROCESSING;
        this.placedAt = new Date();
        this.deliveredAt = null;
    }
}
