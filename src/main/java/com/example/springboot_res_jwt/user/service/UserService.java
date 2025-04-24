package com.example.springboot_res_jwt.user.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.springboot_res_jwt.user.dto.CreateUserRequest;
import com.example.springboot_res_jwt.user.model.User;
import com.example.springboot_res_jwt.user.model.UserRole;
import com.example.springboot_res_jwt.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserRequest createUserRequest, Set<UserRole> roles) {
        User user = User.builder().username(createUserRequest.username())
                .password(passwordEncoder.encode(createUserRequest.password()))
                .email(createUserRequest.email()).avatar(createUserRequest.avatar())
                .firstName(createUserRequest.firstName()).lastName(createUserRequest.lastName())
                .roles(roles).build();
        return userRepository.save(user);
    }

    public User createUserWithUserRole(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, Set.of(UserRole.USER));
    }

    public User createUserWithAdminRole(CreateUserRequest createUserRequest) {
        return createUser(createUserRequest, Set.of(UserRole.ADMIN));
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> getById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> edit(User user) {
        return userRepository.findById(user.getId()).map(existingUser -> {
            existingUser.setEmail(user.getEmail());
            existingUser.setAvatar(user.getAvatar());
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            return userRepository.save(existingUser);
        });
    }

    public Optional<User> editPassword(UUID id, String newPassword) {
        return userRepository.findById(id).map(existingUser -> {
            existingUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(existingUser);
        });
    }

    public void deleteById(UUID id) {
        if (userRepository.existsById(id))
            userRepository.deleteById(id);
    }

    public void delete(User user) {
        userRepository.deleteById(user.getId());
    }
}
