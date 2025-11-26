package com.t3h.eshop.service;

import com.t3h.eshop.storage.dto.UserProfileReq;
import com.t3h.eshop.storage.entity.UserInfo;

public interface UserService {
    UserInfo getUserById(Integer userId);
    void updateUserProfile(UserProfileReq req);
}