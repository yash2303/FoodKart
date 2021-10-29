package com.yashasvi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class OrderDetails {
    private String restaurantId;
    private String orderId;
}
