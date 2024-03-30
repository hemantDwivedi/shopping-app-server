package com.dev.shoppingapp.repository;

import com.dev.shoppingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
