package com.dev.shoppingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDetails> handleApiException(ApiException apiException){
        ErrorDetails errorDetails = new ErrorDetails(apiException.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<OrderErrorDetails> handleResourceNotFoundException(OrderNotFoundException orderNotFoundException){

        OrderErrorDetails orderErrorDetails = new OrderErrorDetails(
                orderNotFoundException.getOrderId(),
                orderNotFoundException.getDescription()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(orderErrorDetails);
    }
}
