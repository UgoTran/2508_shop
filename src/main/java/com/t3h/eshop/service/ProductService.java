package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    //READ
    Page<Product> getAllProducts(int pageNo, int pageSize);

    //CREATE
    Product createProduct(Product product);

    //DELETE
    void deleteProduct(Integer id);

    //DETAIL
    Product getProductById(Integer id);

    //UPDATE
    Product updateProduct(Integer id, Product product);

    //Nút ON/OFF
    void toggleProductStatus(Integer id);

    //Tìm kiếm bằng text box
    Page<Product> searchProducts(String keyword, int pageNo, int pageSize);
}
