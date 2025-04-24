package com.example.springboot_res_jwt.user.dto;

public record CreateUserRequest(String username, String password, String verifyPassword,
        String email, String firstName, String lastName, String avatar) {

}
