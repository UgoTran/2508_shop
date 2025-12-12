package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.SubCategoryService;
import com.t3h.eshop.storage.entity.SubCategory;
import com.t3h.eshop.storage.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> findAll() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory findById(Integer id) {
        return subCategoryRepository.findById(id).orElse(null);
    }

    @Override
    public SubCategory create(SubCategory subCategoryRequest) {
        subCategoryRequest.setSubCategoryId(null);
        return subCategoryRepository.save(subCategoryRequest);
    }

    @Override
    public SubCategory update(Integer id, SubCategory subCategoryRequest) {

        SubCategory existingSubCategory = subCategoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SubCategory ID: !" + id + " not Found!"));

        if (subCategoryRequest.getTitle() == null || subCategoryRequest.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("Title không được để trống!");
        }

        existingSubCategory.setTitle(subCategoryRequest.getTitle());
        existingSubCategory.setIsActive(subCategoryRequest.getIsActive());
        return subCategoryRepository.save(existingSubCategory);
    }


    @Override
    @Transactional // chạy lệnh @Modifying
    public void delete(Integer id) {
        if (subCategoryRepository.existsById(id)) {
            // Gọi hàm xóa cưỡng chế (Force Delete)
            subCategoryRepository.deleteByIdForce(id);
        } else {
            throw new RuntimeException("Không tìm thấy ID: " + id);
        }
    }

    @Override
    public SubCategory findByTitle(String title) {
        return subCategoryRepository.findByTitle(title);
    }

    @Override
    public List<SubCategory> getAllActiveSubCategories() {
        return subCategoryRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getIsActive()))
                .toList();
    }

}