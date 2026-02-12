package com.spring.client;

import com.spring.model.UserResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserClient {

    private final RestTemplate restTemplate;

    public UserClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public String getUserEmail(Long userId) {
        String url = "http://localhost:8000/api/users/" + userId + "/email"; 
        try {
            UserResponse response = restTemplate.getForObject(url, UserResponse.class);
            return response != null ? response.getEmail() : null;
        } catch (Exception e) {
            System.out.println("Error calling User Service: " + e.getMessage());
            return null;
        }
    }
}
