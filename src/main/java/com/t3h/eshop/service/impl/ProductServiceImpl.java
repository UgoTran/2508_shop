package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getById(Integer productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product ID " + productId + " not found"));
    }
}
