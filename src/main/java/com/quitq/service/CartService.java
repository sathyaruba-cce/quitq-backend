package com.quitq.service;

import com.quitq.dto.CartRequest;
import com.quitq.dto.CartResponse;

import java.util.List;

public interface CartService {
    void addToCart(String userEmail, CartRequest request);
    List<CartResponse> getUserCart(String userEmail);
    void updateCartItem(String userEmail, CartRequest request);
    void removeCartItem(String userEmail, Long productId);
    void clearCart(String userEmail);
}
