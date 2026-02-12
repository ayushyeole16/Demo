package com.spring.util;


public class JwtTest {
    public static void main(String[] args) {
        String token = JwtUtil.generateToken("user123");
        System.out.println("Generated Token: " + token);
    }
}
