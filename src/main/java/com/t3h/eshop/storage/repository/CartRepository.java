package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findCartByUserInfoUserId(Integer userId);

}
