package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.ProductService;
import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Page<Product> getAllProducts(int pageNo, int pageSize) {
        // PageRequest.of(index, size): index bắt đầu từ 0
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id)
                .orElse(null);
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Product existingProduct = productRepository.findById(id).get();

        existingProduct.setProductName(product.getProductName());
        existingProduct.setCategoryName(product.getCategoryName());
        existingProduct.setProductType(product.getProductType());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setActive(product.isActive());

        return productRepository.save(existingProduct);
    }

    @Override
    public void toggleProductStatus(Integer id) {

        Product product = productRepository.findById(id).get();

        product.setActive(!product.isActive());

        productRepository.save(product);
    }

    @Override
    public Page<Product> searchProducts(String keyword, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        if (keyword != null) {
            return productRepository.searchProducts(keyword, pageable);
        }
        return productRepository.findAll(pageable);
    }
}
