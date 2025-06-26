package com.quitq.service;

import com.quitq.dto.CartRequest;
import com.quitq.dto.CartResponse;
import com.quitq.entity.CartItem;
import com.quitq.entity.Product;
import com.quitq.entity.User;
import com.quitq.repository.CartRepository;
import com.quitq.repository.ProductRepository;
import com.quitq.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Override
    public void addToCart(String userEmail, CartRequest request) {
        User user = getUserByEmail(userEmail);
        Product product = getProductById(request.getProductId());

        Optional<CartItem> existing = cartRepository.findByUser(user).stream()
            .filter(item -> item.getProduct().getId().equals(product.getId()))
            .findFirst();

        if (existing.isPresent()) {
            CartItem item = existing.get();
            item.setQuantity(item.getQuantity() + request.getQuantity());
            cartRepository.save(item);
        } else {
            CartItem item = CartItem.builder()
                    .user(user)
                    .product(product)
                    .quantity(request.getQuantity())
                    .build();
            cartRepository.save(item);
        }
    }

    @Override
    public List<CartResponse> getUserCart(String userEmail) {
        User user = getUserByEmail(userEmail);
        List<CartItem> cartItems = cartRepository.findByUser(user);
        return cartItems.stream().map(item -> CartResponse.builder()
                .id(item.getId())
                .productName(item.getProduct().getName())
                .productPrice(item.getProduct().getPrice())
                .quantity(item.getQuantity())
                .totalPrice(item.getQuantity() * item.getProduct().getPrice())
                .build()).collect(Collectors.toList());
    }

    @Override
    public void updateCartItem(String userEmail, CartRequest request) {
        User user = getUserByEmail(userEmail);
        List<CartItem> items = cartRepository.findByUser(user);

        for (CartItem item : items) {
            if (item.getProduct().getId().equals(request.getProductId())) {
                item.setQuantity(request.getQuantity());
                cartRepository.save(item);
                break;
            }
        }
    }

    @Override
    public void removeCartItem(String userEmail, Long productId) {
        User user = getUserByEmail(userEmail);
        cartRepository.deleteByUserAndProductId(user, productId);
    }

    @Override
    public void clearCart(String userEmail) {
        User user = getUserByEmail(userEmail);
        List<CartItem> items = cartRepository.findByUser(user);
        cartRepository.deleteAll(items);
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    private Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
