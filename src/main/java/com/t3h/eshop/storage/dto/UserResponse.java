package com.t3h.eshop.storage.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

// Dùng để trả về đầy đủ thông tin chi tiết của User.
// Admin xem danh sách hoặc chi tiết user.
// Hiển thị dữ liệu cũ lên Form để xem xem mình cần sửa thông tin gì hay k?
public class UserResponse {
    Integer userId;
    String username;
    String name;
    String email;
    String phoneNumber;
    String image;
    Integer isBan;
    LocalDateTime createdAt;
}
