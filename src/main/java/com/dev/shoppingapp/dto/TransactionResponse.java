package com.dev.shoppingapp.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionResponse {
    private Integer userId;
    private Integer orderId;
    private Integer transactionId;
    private String status;
}
