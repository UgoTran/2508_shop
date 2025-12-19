package com.t3h.eshop.service;

import com.t3h.eshop.storage.dto.UserCreateRequest;
import com.t3h.eshop.storage.dto.UserUpdateRequest;
import com.t3h.eshop.storage.dto.UserResponse;

import java.util.List;

public interface UserService {
    //1. Create user
    public UserResponse createUser(UserCreateRequest userCreateRequest);

    //2. Read all user
    public List<UserResponse> findAll();

    //3. Update user by Id
    public UserResponse updateUser(Integer Id, UserUpdateRequest userUpdateRequest);

    //4. Delete user by Id
    public void deleteUser(Integer id);

    //5. Profile user by Id
    public UserResponse getUserById(Integer id);

    public UserResponse toggleBanStatus(Integer id);
}
