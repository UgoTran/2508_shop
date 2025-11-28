package com.t3h.eshop.storage.dto;

import lombok.Data;

@Data
public class UserProfileReq {
    private Integer userId;
    private String name;
    private String phoneNumber;

}