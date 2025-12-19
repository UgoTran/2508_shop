package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByTitle(String title);
}
