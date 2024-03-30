package com.dev.shoppingapp.service;

import com.dev.shoppingapp.dto.CreateOrderResponse;
import com.dev.shoppingapp.dto.ListOrderResponse;
import com.dev.shoppingapp.dto.OrderResponse;
import com.dev.shoppingapp.dto.TransactionResponse;
import com.dev.shoppingapp.model.Coupon;
import com.dev.shoppingapp.model.Inventory;

import java.util.List;

public interface IShoppingService {
    CreateOrderResponse createOrder(Integer userId, Integer qty, String coupon);
    TransactionResponse createPayment(Integer userId, Integer orderId, Integer amount);
    Inventory getInventory();
    Coupon fetchCoupons();
    List<ListOrderResponse> getAllOrders(Integer userId);
    OrderResponse getOrderByUserId(Integer userId, Integer orderId);
}
