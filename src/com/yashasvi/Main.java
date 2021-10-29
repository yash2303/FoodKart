package com.yashasvi;

import java.util.HashMap;
import java.util.Map;

import com.yashasvi.controller.OrderingService;
import com.yashasvi.controller.RestaurantService;
import com.yashasvi.dao.DAO;
import com.yashasvi.strategies.LowestOrderAmountStrategy;
import com.yashasvi.strategies.RestaurantSelectionStrategy;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        DAO dao = new DAO();
        RestaurantSelectionStrategy restaurantSelectionStrategy = new LowestOrderAmountStrategy();
        OrderingService orderingService = new OrderingService(dao, restaurantSelectionStrategy);
        RestaurantService restaurantService = new RestaurantService(dao);

        restaurantService.addRestaurant("resta1",
            new HashMap<>(Map.of("king_burger", 10.0, "samosa_pizza", 20.0, "alu_pasta", 19.0)),
            15);
        restaurantService.addRestaurant("resta2",
            new HashMap<>(Map.of("bendi_macaroni", 12.0, "samosa_pizza", 25.0, "alu_pasta", 19.0)),
            12);
        restaurantService.updateMenu("resta1", new HashMap<>(Map.of("bendi_macaroni", 8.0, "king_burger", 15.0)));

        restaurantService.printAllRestaurants();

        System.out.println(orderingService.book("cust1", Map.of("bendi_macaroni", 3, "samosa_pizza", 2)));

        System.out.println(orderingService.book("cust1", Map.of("bendi_macaroni", 6, "samosa_pizza", 2)));

        restaurantService.printAllRestaurants();
        orderingService.printAllOrders();
        Thread.sleep(1000); // Prep time
        orderingService.markAsDelivered("order1");
        orderingService.printAllOrders();
        System.out.println(orderingService.book("cust1", Map.of("bendi_macaroni", 6, "samosa_pizza", 2)));
        restaurantService.printAllRestaurants();
    }
}