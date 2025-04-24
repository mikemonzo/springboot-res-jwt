package com.example.springboot_res_jwt.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;
import com.example.springboot_res_jwt.user.model.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public record UserResponse(UUID id, String username, String email, String firstName,
        String lastName, String avatar, @JsonFormat(shape = JsonFormat.Shape.STRING,
                pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime createdAt) {

    public static UserResponse of(User user) {
        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getAvatar(), user.getCreatedAt());
    }

}
