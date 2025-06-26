package com.quitq.service;

import com.quitq.entity.Order;

import java.util.List;

public interface OrderService {
    Order placeOrder(Order order);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(String email);
}
