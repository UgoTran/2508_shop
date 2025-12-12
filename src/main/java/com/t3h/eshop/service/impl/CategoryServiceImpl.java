package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.CategoryService;
import com.t3h.eshop.storage.entity.Category;
import com.t3h.eshop.storage.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Integer id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category ID: " + id + " not found"));
    }

    public Category create(Category categoryRequest) {

        categoryRequest.setCategoryId(null);

        //Valid Data
        if(categoryRequest.getTitle() == null || categoryRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title của Category không được để trống!");
        }

        return categoryRepository.save(categoryRequest);
    }

    public Category update(Integer id, Category categoryRequest) {

        //Lấy entity từ database
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category ID: " + id + " không tìm thấy!"));

        if(categoryRequest.getTitle() == null || categoryRequest.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title của Category không được để trống!");
        }

        existingCategory.setTitle(categoryRequest.getTitle());
        existingCategory.setIsActive(categoryRequest.getIsActive());
        existingCategory.setImageLink(categoryRequest.getImageLink());

        return categoryRepository.save(existingCategory);
    }
    
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getAllActiveCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getIsActive()))
                .toList();
    }

    @Override
    public Category findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

}
