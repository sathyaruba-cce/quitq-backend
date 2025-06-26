package com.quitq.service;

import com.quitq.dto.AuthRequest;
import com.quitq.dto.RegisterRequest;

public interface UserService {
    void register(RegisterRequest request);
    String login(AuthRequest request);
}
