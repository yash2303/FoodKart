package com.yashasvi.exceptions;

public class NoAvailableRestaurantsException extends RuntimeException {
    public NoAvailableRestaurantsException(String message) {
        super(message);
    }
}
