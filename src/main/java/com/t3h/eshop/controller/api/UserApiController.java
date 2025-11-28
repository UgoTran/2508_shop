package com.t3h.eshop.controller.api;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.ResponseDTO;
import com.t3h.eshop.storage.dto.UserProfileReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO<Void>> updateProfile(@RequestBody UserProfileReq req) {
        try {
            req.setUserId(1);

            userService.updateUserProfile(req);

            return ResponseEntity.ok(
                    ResponseDTO.<Void>builder()
                            .httpCode(200)
                            .message("Cập nhật thành công!")
                            .build()
            );

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(
                    ResponseDTO.<Void>builder()
                            .httpCode(400)
                            .message(e.getMessage())
                            .build()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    ResponseDTO.<Void>builder()
                            .httpCode(500)
                            .message("Lỗi hệ thống: " + e.getMessage())
                            .build()
            );
        }
    }
}