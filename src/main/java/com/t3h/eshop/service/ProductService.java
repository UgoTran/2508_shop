package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Product;
import com.t3h.eshop.storage.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    // Hàm lấy sản phẩm theo TÊN danh mục
    public List<Product> getProductsByCategory(String categoryName);
}
