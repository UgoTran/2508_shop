package com.t3h.eshop.controller.api;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.UserProfileReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {

    @Autowired
    private UserService userService;

    @PutMapping("/update")
    public ResponseEntity<?> updateProfile(@RequestBody UserProfileReq req) {
        Map<String, String> response = new HashMap<>();
        try {
            req.setUserId(1);

            userService.updateUserProfile(req);

            response.put("status", "success");
            response.put("message", "Cập nhật thành công!");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            response.put("status", "error");
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Lỗi hệ thống: " + e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }
}