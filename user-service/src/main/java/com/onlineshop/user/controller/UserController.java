package com.onlineshop.user.controller;

import com.onlineshop.user.model.User;
import com.onlineshop.user.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.onlineshop.user.dto.LoginRequest;
import com.onlineshop.user.dto.AuthResponse;
import com.onlineshop.user.dto.UpdateProfileRequest;
import com.onlineshop.user.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

//@PostMapping("/api/products")
//public ResponseEntity<?> addProduct(@RequestHeader("Authorization") String token, ...) {
//    String role = jwtUtil.extractRole(token); // implement this JWT claim
//    if (!"ADMIN".equals(role)) {
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Forbidden");
//    }
//    // Product create code here
//}

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    // implement login, update profile, get profile APIs
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            User user = userService.login(request.getEmail(), request.getPassword());
            // Pass user's role to JWT
            String token = jwtUtil.generateToken(user.getEmail(), user.getRole().name());
            return ResponseEntity.ok(new AuthResponse(token, user.getName(), user.getEmail()));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        // Optional: perform server-side token invalidation if using token blacklist
        return ResponseEntity.ok("Logged out successfully");
    }

    // In UserController (user-service):
    @GetMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String header) {
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing token");
        }
        try {
            String token = header.substring(7);
            String email = jwtUtil.extractEmail(token);
            String role = jwtUtil.extractRole(token); // works now!
            Map<String, Object> info = new HashMap<>();
            info.put("email", email);
            info.put("role", role);
            return ResponseEntity.ok(info);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        System.out.println(request);
//        User user = (User) authentication.getPrincipal(); // assuming User is principal
//        return ResponseEntity.ok(user);
        String token = extractToken(request);

        String email = jwtUtil.extractEmail(token);
        User user = userService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(
            HttpServletRequest request,
            @RequestBody UpdateProfileRequest updateRequest) {
        String token = extractToken(request);
        String email = jwtUtil.extractEmail(token);
        User user = userService.getUserByEmail(email);
        String name = updateRequest.getName() != null ? updateRequest.getName() : user.getName();
        String address = updateRequest.getAddress() != null ? updateRequest.getAddress() : user.getAddress();
        userService.updateProfile(user, name, address, updateRequest.getPassword());
        return ResponseEntity.ok(user);
    }

    private String extractToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        System.out.println(header);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        throw new RuntimeException("Missing or invalid Authorization header");
    }
}