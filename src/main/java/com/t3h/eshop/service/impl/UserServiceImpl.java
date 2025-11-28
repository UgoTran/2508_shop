package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.UserProfileReq;
import com.t3h.eshop.storage.entity.UserInfo;
import com.t3h.eshop.storage.repository.UserInfoRepository;
import org.apache.commons.lang3.StringUtils; // Import thư viện
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserInfo getUserById(Integer userId) {
        if (userId == null) {
            return null;
        }
        return userInfoRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserProfile(UserProfileReq req) {
        if (req.getUserId() == null) {
            throw new IllegalArgumentException("User ID không được để trống");
        }

        if (StringUtils.isBlank(req.getName())) {
            throw new IllegalArgumentException("Tên không được để trống");
        }
        if (StringUtils.isBlank(req.getPhoneNumber())) {
            throw new IllegalArgumentException("Số điện thoại không được để trống");
        }

        UserInfo user = userInfoRepository.findById(req.getUserId())
                .orElseThrow(() -> new RuntimeException("User không tồn tại trong hệ thống"));

        user.setName(req.getName());
        user.setPhoneNumber(req.getPhoneNumber());

        userInfoRepository.save(user);
    }
}