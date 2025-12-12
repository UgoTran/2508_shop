package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> findAll();

    public Category findById(Integer id);

    public Category create(Category categoryRequest);

    public Category update(Integer id, Category categoryRequest);

    public void delete(Integer id);

    List<Category> getAllActiveCategories();

    public Category findByTitle(String title);
}
