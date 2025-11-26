package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.UserProfileReq;
import com.t3h.eshop.storage.entity.UserInfo;
import com.t3h.eshop.storage.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getUserById(Integer userId) {
        return userInfoRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public void updateUserProfile(UserProfileReq req) {
        UserInfo user = userInfoRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User to update not found"));

        if (req.getName() == null || req.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        if (req.getPhoneNumber() == null || req.getPhoneNumber().trim().isEmpty()) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }

        user.setName(req.getName());
        user.setPhoneNumber(req.getPhoneNumber());

        userInfoRepository.save(user);
    }
}