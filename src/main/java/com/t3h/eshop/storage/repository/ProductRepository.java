package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // SELECT * FROM products WHERE category_name = ?
    List<Product> findByCategoryName(String categoryName);

    // Chỉ lấy sản phẩm đang "active" (isActive = true)
    // List<Product> findByCategoryNameAndIsActiveTrue(String categoryName);
}
