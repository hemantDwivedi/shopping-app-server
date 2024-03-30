package com.dev.shoppingapp.repository;

import com.dev.shoppingapp.dto.OrderResponse;
import com.dev.shoppingapp.model.Order;
import com.dev.shoppingapp.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Transaction findByOrder(Order order);
}
