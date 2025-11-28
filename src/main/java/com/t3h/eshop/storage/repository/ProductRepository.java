package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page; // Import Page
import org.springframework.data.domain.Pageable; // Import Pageable
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p WHERE " +
            "p.productName LIKE %?1% OR " +
            "p.categoryName LIKE %?1% OR " +
            "p.productType LIKE %?1%")
    Page<Product> searchProducts(String keyword, Pageable pageable);
}
