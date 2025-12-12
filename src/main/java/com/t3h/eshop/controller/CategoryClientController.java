package com.t3h.eshop.controller;

import com.t3h.eshop.service.CategoryService;
import com.t3h.eshop.storage.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "*")
public class CategoryClientController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getClientCategories() {
        return ResponseEntity.ok(categoryService.getAllActiveCategories());
    }
    @GetMapping("/{title}")
    public ResponseEntity<?> getClientCategory(@PathVariable("title") String title) {
        Category category = categoryService.findByTitle(title);
        if (category != null) {
            return ResponseEntity.ok(category);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
