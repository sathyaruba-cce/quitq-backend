package com.quitq.controller;

import com.quitq.dto.CartRequest;
import com.quitq.dto.CartResponse;
import com.quitq.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;

    private String extractEmailFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("⚠️ Authentication object: " + authentication);

        if (authentication == null || !(authentication.getPrincipal() instanceof UserDetails)) {
            throw new RuntimeException("User not authenticated");
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("✅ Extracted email: " + userDetails.getUsername());
        return userDetails.getUsername();
    }

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestBody CartRequest request) {
    	String email = "testuser@example.com"; // or any test email

        cartService.addToCart(email, request);
        return ResponseEntity.ok("Item added to cart");
    }

    @GetMapping
    public ResponseEntity<List<CartResponse>> getCart() {
        String email = extractEmailFromSecurityContext();
        return ResponseEntity.ok(cartService.getUserCart(email));
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateCart(@RequestBody CartRequest request) {
        String email = extractEmailFromSecurityContext();
        cartService.updateCartItem(email, request);
        return ResponseEntity.ok("Cart updated");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long productId) {
        String email = extractEmailFromSecurityContext();
        cartService.removeCartItem(email, productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<String> clearCart() {
        String email = extractEmailFromSecurityContext();
        cartService.clearCart(email);
        return ResponseEntity.ok("Cart cleared");
    }
}
