package com.dev.shoppingapp;

import com.dev.shoppingapp.model.Coupon;
import com.dev.shoppingapp.model.Inventory;
import com.dev.shoppingapp.model.User;
import com.dev.shoppingapp.repository.CouponRepository;
import com.dev.shoppingapp.repository.InventoryRepository;
import com.dev.shoppingapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@AllArgsConstructor
@Component
public class InitDatabase implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) {
        User user = User.builder().userId(1).name("Hemant").build();
        Coupon cou = Coupon.builder().couponId(11).user(user).off5(5).off10(10).build();
        Inventory inventory = new Inventory(1, 0, 100, 100);

        userRepository.save(user);
        inventoryRepository.save(inventory);
        couponRepository.save(cou);
        log.info("Database initialized");
    }

}