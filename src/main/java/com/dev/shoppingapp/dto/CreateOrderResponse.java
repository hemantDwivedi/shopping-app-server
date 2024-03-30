package com.dev.shoppingapp.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateOrderResponse {
    private Integer orderId;
    private Integer userId;
    private Integer quantity;
    private Integer amount;
    private String coupon;
}
