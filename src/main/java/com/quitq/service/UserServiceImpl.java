package com.quitq.service;

import com.quitq.dto.AuthRequest;
import com.quitq.dto.RegisterRequest;
import com.quitq.entity.User;
import com.quitq.repository.UserRepository;
import com.quitq.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Override
    public void register(RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .gender(request.getGender())
                .email(request.getEmail())
                .password(request.getPassword())
                .contactNumber(request.getContactNumber())
                .address(request.getAddress())
                .role("USER")
                .build();

        userRepository.save(user);
    }

    @Override
    public String login(AuthRequest request) {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getEmail());
    }
}
