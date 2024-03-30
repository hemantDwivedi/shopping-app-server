package com.dev.shoppingapp.service;

import com.dev.shoppingapp.dto.CreateOrderResponse;
import com.dev.shoppingapp.dto.ListOrderResponse;
import com.dev.shoppingapp.dto.OrderResponse;
import com.dev.shoppingapp.dto.TransactionResponse;
import com.dev.shoppingapp.exception.ApiException;
import com.dev.shoppingapp.exception.OrderNotFoundException;
import com.dev.shoppingapp.model.*;
import com.dev.shoppingapp.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ShoppingServiceImpl implements IShoppingService {

    public static final String USER_NOT_FOUND = "User not found";
    private UserRepository userRepository;
    private OrderRepository orderRepository;
    private CouponRepository couponRepository;
    private InventoryRepository inventoryRepository;
    private TransactionRepository transactionRepository;

    @Override
    public CreateOrderResponse createOrder(Integer userId, Integer qty, String coupon) {
        Inventory inventory = inventoryRepository.findAll().get(0);

        if (qty > inventory.getAvailable()){
            throw new ApiException("Invalid quantity");
        } else {
            inventory.setAvailable(inventory.getAvailable() - qty);
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(USER_NOT_FOUND));
        Coupon userCoupon = couponRepository.findByUser(user);

        int discount;
        if (coupon.equals("OFF5")){
            discount = userCoupon.getOff5();
            userCoupon.setOff5(0);
        } else {
            discount = userCoupon.getOff10();
            userCoupon.setOff10(0);
        }

        // check coupon is used or not
        if (discount == 0){
            throw new ApiException("Invalid Coupon");
        }

        // calculating final price after apply coupon
        int totalPrice = qty * inventory.getPrice();
        int discountAmount = discount * (totalPrice/100);
        int finalPrice = totalPrice - discountAmount;

        Order order = Order.builder()
                .quantity(qty)
                .amount(finalPrice)
                .user(user)
                .coupon(coupon)
                .date(getOrderDate())
                .build();

        orderRepository.save(order);
        couponRepository.save(userCoupon);
        inventoryRepository.save(inventory);
        log.info("Order saved to database...");

        return orderToOrderResponse(order);
    }

    @Override
    public TransactionResponse createPayment(Integer userId, Integer orderId, Integer amount) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(USER_NOT_FOUND));
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new ApiException("Order not found"));

        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setOrder(order);
        transaction.setStatus("successful");
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        return transactionToTransactionResponse(transaction);
    }


    @Override
    public Inventory getInventory() {
        return inventoryRepository.findAll().get(0);
    }

    @Override
    public Coupon fetchCoupons() {
        return couponRepository.findAll().get(0);
    }

    @Override
    public List<ListOrderResponse> getAllOrders(Integer userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ApiException(USER_NOT_FOUND));

        List<Order> orderList = orderRepository.findAllByUser(user);

        return orderList
                .stream()
                .map(this::orderToListOrderResponse)
                .toList();
    }

    @Override
    public OrderResponse getOrderByUserId(Integer userId, Integer orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId, "Order not found"));
        Transaction transaction = transactionRepository.findByOrder(order);

        return orderToOrderResponse(order, transaction);
    }

    private OrderResponse orderToOrderResponse(Order order, Transaction transaction){
        return
                OrderResponse.builder()
                        .orderId(order.getOrderId())
                        .amount(order.getAmount())
                        .date(order.getDate())
                        .coupon(order.getCoupon())
                        .transactionId(transaction.getTransactionId())
                        .status(transaction.getStatus())
                        .build();
    }

    private ListOrderResponse orderToListOrderResponse(Order order){
        return ListOrderResponse.builder()
                .orderId(order.getOrderId())
                .amount(order.getAmount())
                .coupon(order.getCoupon())
                .date(order.getDate())
                .build();
    }

    private TransactionResponse transactionToTransactionResponse(Transaction transaction) {
        return
                new TransactionResponse(
                      transaction.getUser().getUserId(),
                      transaction.getOrder().getOrderId(),
                      transaction.getTransactionId(),
                      transaction.getStatus()
                );
    }

    private CreateOrderResponse orderToOrderResponse(Order order) {
        return
                CreateOrderResponse.builder()
                        .orderId(order.getOrderId())
                        .userId(order.getUser().getUserId())
                        .quantity(order.getQuantity())
                        .amount(order.getAmount())
                        .coupon(order.getCoupon())
                        .build();
    }

    private String getOrderDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

}
