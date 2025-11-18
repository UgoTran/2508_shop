package com.Users.users.Storage.repository;

import com.Users.users.Storage.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface UsersRepository extends JpaRepository<Users,Integer> {


    @Query("SELECT u FROM Users u WHERE " +
            "LOWER(u.name) LIKE LOWER(CONCAT('%', :keyword, '%'))"
    )
    Page<Users> searchUsersByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
