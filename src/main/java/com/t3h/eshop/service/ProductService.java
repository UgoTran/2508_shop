package com.t3h.eshop.service;

import com.t3h.eshop.storage.entity.Product;

public interface ProductService {

    Product getById(Integer productId);
}
