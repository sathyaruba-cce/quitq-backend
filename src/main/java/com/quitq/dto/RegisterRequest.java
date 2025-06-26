package com.quitq.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String gender;
    private String email;
    private String password;
    private String contactNumber;
    private String address;
}
