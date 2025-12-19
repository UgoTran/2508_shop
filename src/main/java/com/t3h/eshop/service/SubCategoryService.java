package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Category;
import com.t3h.eshop.storage.entity.SubCategory;
import java.util.List;

public interface SubCategoryService {

    public List<SubCategory> findAll();

    public SubCategory findById(Integer id);

    public SubCategory create(SubCategory subCategoryRequest);

    public SubCategory update(Integer id, SubCategory subCategoryRequest);

    public void delete(Integer id);

    SubCategory findByTitle(String title);

    List<SubCategory> getAllActiveSubCategories();
}