package com.dev.shoppingapp.model;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Transaction {
    @Id
    @GeneratedValue
    private Integer transactionId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToOne
    @JoinColumn(name = "orderId")
    private Order order;
    @Column(unique = true, nullable = false)
    private String status;
    private Integer amount;
}
