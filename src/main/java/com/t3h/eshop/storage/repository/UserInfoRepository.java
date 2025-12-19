package com.t3h.eshop.storage.repository;

import com.t3h.eshop.storage.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
    Optional<UserInfo> findByEmail(String email);

    Optional<UserInfo> findByPhoneNumber(String phoneNumber);

    Optional<UserInfo> findByName(String name);

    boolean existsById(Integer id);

    boolean existsByEmailOrPhoneNumber(String email, String phoneNumber);

    Optional<UserInfo> findByUsername(String username);
}
