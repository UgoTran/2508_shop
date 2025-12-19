package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Category;
import com.t3h.eshop.storage.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> findAll();

    public Product findById(Integer id);

    public Product create(Product productRequest);

    public Product update(Integer id, Product productRequest);

    public void delete(Integer id);

    Product findByTitle(String title);

    List<Product> getAllActiveProducts();
}
