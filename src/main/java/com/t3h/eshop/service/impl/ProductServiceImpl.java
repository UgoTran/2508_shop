package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.ProductService;

import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Integer id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm theo ID: " + id));
    }

    @Override
    public Product create(Product productRequest) {

        productRequest.setProductId(null);

        if (productRequest.getTitle() == null || productRequest.getTitle().trim().isEmpty()){
            throw new IllegalArgumentException("Title không được để trống!");
        }

        return productRepository.save(productRequest);
    }

    @Override
    public Product update(Integer id, Product productRequest) {
        Product existingProduct = productRepository.findById(id)
                                        .orElseThrow(() -> new RuntimeException("Product ID: " + id + " không tìm thấy!"));
        existingProduct.setTitle(productRequest.getTitle());
        existingProduct.setSellingPrice(productRequest.getSellingPrice());
        existingProduct.setShortDescription(productRequest.getShortDescription());
        existingProduct.setProductFeatures(productRequest.getProductFeatures());
        existingProduct.setIsActive(productRequest.getIsActive());
        existingProduct.setImage1(productRequest.getImage1());
        existingProduct.setImage2(productRequest.getImage2());

        if (productRequest.getSubCategory() != null) {
            existingProduct.setSubCategory(productRequest.getSubCategory());
        }

        return  productRepository.save(productRequest);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    public List<Product> getAllActiveProducts() {
        return productRepository.findAll()
                .stream()
                .filter(c -> Boolean.TRUE.equals(c.getIsActive()))
                .toList();
    }
}
