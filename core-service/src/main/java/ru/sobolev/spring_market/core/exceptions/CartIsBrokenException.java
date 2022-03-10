package ru.sobolev.spring_market.core.exceptions;

public class CartIsBrokenException extends RuntimeException {
    public CartIsBrokenException(String message) {
        super(message);
    }
}
