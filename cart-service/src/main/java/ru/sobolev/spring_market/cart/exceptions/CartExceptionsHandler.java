package ru.sobolev.spring_market.cart.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.sobolev.spring_market.api.exceptions.AppError;
import ru.sobolev.spring_market.api.exceptions.ResourceNotFoundException;

@ControllerAdvice
@ResponseBody
public class CartExceptionsHandler {
    @ExceptionHandler(BadConnectionToCoreException.class)
    public ResponseEntity<AppError> coreServiceConnectionError(BadConnectionToCoreException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<AppError> resourceNotFoundError(ResourceNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
