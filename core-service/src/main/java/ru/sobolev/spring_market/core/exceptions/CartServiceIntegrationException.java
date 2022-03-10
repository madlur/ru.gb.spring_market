package ru.sobolev.spring_market.core.exceptions;

public class CartServiceIntegrationException extends RuntimeException {
    public CartServiceIntegrationException(String message) {
        super(message);
    }
}
