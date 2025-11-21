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
public class OrdersServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getById(Integer productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        Product product = null;
        if(optionalProduct.isEmpty()) throw new RuntimeException("Id " + productId + " not founded");
        else product = optionalProduct.get();
        return product;
    }
}
