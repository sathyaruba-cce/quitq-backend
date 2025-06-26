package com.quitq.service;

import com.quitq.dto.AuthRequest;
import com.quitq.dto.AuthResponse;
import com.quitq.entity.User;
import com.quitq.repository.UserRepository;
import com.quitq.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(AuthRequest request) {
        User user = User.builder()
            .name(request.getName())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .gender(request.getGender())
            .contactNumber(request.getContactNumber())
            .address(request.getAddress())
            .role(request.getRole())  // "USER", "SELLER", or "ADMIN"
            .build();

        userRepo.save(user);
        return "Registration successful";
    }

    public AuthResponse login(AuthRequest request) {
        User user = userRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token);
    }
}
