package ru.sobolev.spring_market.cart.exceptions;

public class CartIsBrokenException extends RuntimeException {
    public CartIsBrokenException(String message) {
        super(message);
    }
}
