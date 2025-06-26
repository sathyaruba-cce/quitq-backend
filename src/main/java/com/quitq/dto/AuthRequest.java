package com.quitq.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;
    private String email;
    private String password;
    private String gender;
    private String contactNumber;
    private String address;
    private String role;
}
