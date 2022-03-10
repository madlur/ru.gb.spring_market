package ru.sobolev.spring_market.cart.exceptions;

public class BadConnectionToCoreException extends RuntimeException {
    public BadConnectionToCoreException(String message){
        super(message);
    }
}
