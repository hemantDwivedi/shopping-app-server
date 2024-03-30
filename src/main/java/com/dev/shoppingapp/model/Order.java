package com.dev.shoppingapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_gen")
    @SequenceGenerator(name = "order_gen", sequenceName = "order_seq", initialValue = 100, allocationSize = 1)
    private Integer orderId;
    private Integer quantity;
    private Integer amount;
    private String date;
    private String coupon;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
