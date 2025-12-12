package com.t3h.eshop.controller;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductClientController {
    @Autowired
    private ProductService productService;

    @GetMapping("/{title}")
    public ResponseEntity<?> getClientSubCategory(@PathVariable("title") String title) {
        Product product = productService.findByTitle(title);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
