package com.dev.shoppingapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderNotFoundException extends RuntimeException{
    private Integer orderId;
    private String description;
}
