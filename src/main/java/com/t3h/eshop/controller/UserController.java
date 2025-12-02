package com.t3h.eshop.controller;


import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.UserCreateRequest;
import com.t3h.eshop.storage.dto.UserUpdateRequest;
import com.t3h.eshop.storage.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userCreateRequest));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer id,
                                                   @RequestBody UserUpdateRequest userUpdateRequest) {
        return ResponseEntity.ok(userService.updateUser(id, userUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/toggle-ban/{id}")
    public ResponseEntity<UserResponse> toggleBanStatus(@PathVariable Integer id) {
        return ResponseEntity.ok(userService.toggleBanStatus(id));
    }
}
