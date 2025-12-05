package com.t3h.eshop.utils;

import com.t3h.eshop.storage.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // 1. Đánh dấu đây là nơi xử lý lỗi toàn cục
public class GlobalExceptionHandler {

    /**
     * Bắt lỗi IllegalArgumentException
     * (Lỗi do người dùng gửi dữ liệu sai: rỗng, sai định dạng...)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDTO<Object>> handleBadRequest(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ResponseDTO.builder()
                        .httpCode(400)
                        .message(ex.getMessage()) // Lấy message từ Service throw ra
                        .data(null)
                        .build()
        );
    }

    /**
     * Bắt lỗi RuntimeException
     * (Lỗi logic, không tìm thấy dữ liệu...)
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDTO<Object>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body( // Hoặc INTERNAL_SERVER_ERROR tùy logic
                ResponseDTO.builder()
                        .httpCode(404)
                        .message(ex.getMessage())
                        .data(null)
                        .build()
        );
    }

    /**
     * Bắt tất cả các lỗi còn lại mà mình chưa lường trước được (NullPointer, SQL Error...)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDTO<Object>> handleUnwantedException(Exception ex) {
        ex.printStackTrace(); // In lỗi ra console để dev sửa
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseDTO.builder()
                        .httpCode(500)
                        .message("Lỗi hệ thống không xác định. Vui lòng liên hệ Admin.")
                        .data(null)
                        .build()
        );
    }
}