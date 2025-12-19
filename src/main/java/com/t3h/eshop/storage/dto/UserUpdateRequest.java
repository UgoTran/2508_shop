package com.t3h.eshop.storage.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

//Dùng cho việc update thông tin
public class UserUpdateRequest {
    String name;
    String email;
    String phoneNumber;
    Integer isBan;
    String image;
}
