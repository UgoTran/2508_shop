package com.t3h.eshop.controller.api;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.ResponseDTO;
import com.t3h.eshop.storage.dto.UserProfileReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<Void>> updateProfile(@RequestBody UserProfileReq req) {
        // 1. Giả lập ID (sau này lấy từ Token)
        req.setUserId(1);

        // 2. Gọi Service. Nếu lỗi, Service sẽ throw exception -> GlobalExceptionHandler tự bắt.
        userService.updateUserProfile(req);

        // 3. Nếu chạy đến dòng này nghĩa là KHÔNG CÓ LỖI. Trả về thành công.
        return ResponseEntity.ok(
                ResponseDTO.<Void>builder()
                        .httpCode(200)
                        .message("Cập nhật thành công!")
                        .build()
        );
    }
}