package com.dev.shoppingapp.controller;

import com.dev.shoppingapp.dto.CreateOrderResponse;
import com.dev.shoppingapp.dto.ListOrderResponse;
import com.dev.shoppingapp.dto.OrderResponse;
import com.dev.shoppingapp.dto.TransactionResponse;
import com.dev.shoppingapp.model.Coupon;
import com.dev.shoppingapp.model.Inventory;
import com.dev.shoppingapp.service.IShoppingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ShoppingController {

    private IShoppingService shoppingService;

    @PostMapping("/{userId}/order")
    public ResponseEntity<CreateOrderResponse> createOrder(@PathVariable Integer userId, @RequestParam Integer qty, @RequestParam String coupon){
        return ResponseEntity.status(200).body(shoppingService.createOrder(userId, qty, coupon));
    }

    @PostMapping("/{userId}/{orderId}/pay")
    public ResponseEntity<TransactionResponse> createPayment(@PathVariable Integer userId, @PathVariable Integer orderId,
                                                             @RequestParam Integer amount){
        return ResponseEntity.status(200).body(shoppingService.createPayment(userId, orderId, amount));
    }

    @GetMapping("/inventory")
    public ResponseEntity<Inventory> getInventory(){
        return ResponseEntity.status(200).body(shoppingService.getInventory());
    }

    @GetMapping("/fetchCoupons")
    public ResponseEntity<Coupon> getCoupons(){
        return ResponseEntity.status(200).body(shoppingService.fetchCoupons());
    }

    @GetMapping("/{userId}/orders")
    public ResponseEntity<List<ListOrderResponse>> getAllOrders(@PathVariable Integer userId){
        return ResponseEntity.status(200).body(shoppingService.getAllOrders(userId));
    }

    @GetMapping("/{userId}/orders/{orderId}")
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Integer userId, @PathVariable Integer orderId){
        return ResponseEntity.status(200).body(shoppingService.getOrderByUserId(userId, orderId));
    }
}
