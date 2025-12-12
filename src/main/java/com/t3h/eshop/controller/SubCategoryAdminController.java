package com.t3h.eshop.controller;

import com.t3h.eshop.service.SubCategoryService; // Đổi import
import com.t3h.eshop.storage.entity.Category;
import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/sub-category")
@CrossOrigin(origins = "*")
public class SubCategoryAdminController {

    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    public List<SubCategory> getAll() {
        return subCategoryService.findAll();
    }

    @GetMapping("/{id}")
    public SubCategory getOne(@PathVariable Integer id) {
        return subCategoryService.findById(id);
    }

    @PostMapping
    public ResponseEntity<SubCategory> create(@RequestBody SubCategory subCategoryRequest) {
        subCategoryRequest.setSubCategoryId(null);
        SubCategory createdSubCategory = subCategoryService.create(subCategoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubCategory> update(@PathVariable Integer id, @RequestBody SubCategory subCategoryRequest) {
        subCategoryRequest.setSubCategoryId(id);
        SubCategory updated = subCategoryService.update(id, subCategoryRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            subCategoryService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Đã xóa thành công ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("message", "Lỗi: Không thể xóa (Có thể do ràng buộc dữ liệu)"));
        }
    }
}