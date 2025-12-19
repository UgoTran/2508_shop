package com.t3h.eshop.controller;

import com.t3h.eshop.service.SubCategoryService;
import com.t3h.eshop.storage.entity.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/subcategory")
public class SubCategoryClientController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping("/{title}")
    public ResponseEntity<?> getClientSubCategory(@PathVariable("title") String title) {
        SubCategory subcategory = subCategoryService.findByTitle(title);
        if (subcategory != null) {
            return ResponseEntity.ok(subcategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
