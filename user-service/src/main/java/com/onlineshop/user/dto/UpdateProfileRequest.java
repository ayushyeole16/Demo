package com.onlineshop.user.dto;
import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String name;
    private String address;
    private String password; // Optional, if user wants to change password
}