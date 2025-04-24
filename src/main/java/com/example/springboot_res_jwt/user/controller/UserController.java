package com.example.springboot_res_jwt.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.example.springboot_res_jwt.user.dto.CreateUserRequest;
import com.example.springboot_res_jwt.user.model.User;
import com.example.springboot_res_jwt.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<User> createUserWithUserRole(@RequestBody CreateUserRequest request) {
        User user = userService.createUserWithUserRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PostMapping("/auth/register/admin")
    public ResponseEntity<User> createUserWithAdminRole(@RequestBody CreateUserRequest request) {
        User user = userService.createUserWithAdminRole(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

