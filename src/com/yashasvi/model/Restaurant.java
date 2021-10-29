package com.yashasvi.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Restaurant {
    private String restaurantId;
    private Map<String, Double> menu; // ItemName, Price
    private Integer totalCapacity;
    private List<Order> orders;
    private AtomicInteger capacityInUse;

    public void setCapacityInUse(Integer capacityInUse) {
        this.capacityInUse.set(capacityInUse);
    }

    public Restaurant(
        String restaurantId,
        Map<String, Double> menu,
        Integer totalCapacity) {
        this.restaurantId = restaurantId;
        this.menu = menu;
        this.totalCapacity = totalCapacity;
        this.orders = new ArrayList<>();
        this.capacityInUse = new AtomicInteger(0);
    }

    public String toString() {
        return "Restaurant(id=" + this.getRestaurantId() + ", menu=" + this.getMenu() + ", totalCapacity="
            + this.getTotalCapacity() + ", capacityInUse="
            + this.getCapacityInUse() + ")";
    }
}
