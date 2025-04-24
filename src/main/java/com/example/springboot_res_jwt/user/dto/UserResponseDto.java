package com.example.springboot_res_jwt.user.dto;

import java.util.UUID;
import com.example.springboot_res_jwt.user.model.User;

public record UserResponseDto(UUID id, String username, String email, String firstName,
        String lastName, String avatar) {

    // UserResponseDto of User
    public static UserResponseDto of(User user) {
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getAvatar());
    }

}
