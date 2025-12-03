package com.t3h.eshop.controller;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/products")
@CrossOrigin(origins = "*") //Cho phép FE gọi API thoải mái
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/category/{name}")
    public ResponseEntity<?> getProductsByCategory(@PathVariable("name") String categoryName) {
        List<Product> products = productService.getProductsByCategory(categoryName);
        //Chuẩn bị dữ liệu trả về (JSON Format)
        Map<String, Object> response = new HashMap<>();

        if (products.isEmpty()) {
            response.put("status", "error");
            response.put("message", "Không tìm thấy sản phẩm nào trong danh mục: " + categoryName);
            return ResponseEntity.status(404).body(response);
        }

        response.put("status", "success");
        response.put("category", categoryName);
        response.put("count", products.size());
        response.put("data", products);

        return ResponseEntity.ok(response);
    }

}
