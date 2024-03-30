package com.dev.shoppingapp.repository;

import com.dev.shoppingapp.model.Coupon;
import com.dev.shoppingapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByUser(User user);
}
