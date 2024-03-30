package com.dev.shoppingapp.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderResponse {
    private Integer orderId;
    private Integer amount;
    private String date;
    private String coupon;
    private Integer transactionId;
    private String status;
}
