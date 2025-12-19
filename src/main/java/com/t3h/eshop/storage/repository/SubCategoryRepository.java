package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    // Tìm tất cả sub-category đang bật
    List<SubCategory> findByIsActiveTrue();

    SubCategory findByTitle(String title);

    @Modifying
    @Transactional
    @Query("DELETE FROM SubCategory s WHERE s.subCategoryId = :id")
    void deleteByIdForce(Integer id);
}