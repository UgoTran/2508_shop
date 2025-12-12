package com.t3h.eshop.controller;

import com.t3h.eshop.service.CategoryService;
import com.t3h.eshop.storage.entity.Category;
import com.t3h.eshop.storage.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/category")
@CrossOrigin(origins = "*")
public class CategoryAdminController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public Category getOne(@PathVariable Integer id) {
        return categoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Category> create(@RequestBody Category categoryRequest) {
        categoryRequest.setCategoryId(null);
        Category savedCategory = categoryService.create(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable Integer id, @RequestBody Category categoryRequest) {
        categoryRequest.setCategoryId(id);
        Category updated = categoryService.update(id, categoryRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        categoryService.delete(id);
        return ResponseEntity.ok(Map.of("message", "Deleted successfully!"));
    }
}