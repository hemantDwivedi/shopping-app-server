package com.dev.shoppingapp.repository;

import com.dev.shoppingapp.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
}