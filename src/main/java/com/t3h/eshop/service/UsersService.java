package com.t3h.eshop.service;


import com.t3h.eshop.storage.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UsersService {

    //READ
    public Page<Users> findAllUsers(int page, int pageSize);

    //CREATE
    public Users createUsers(Users users);

    //UPDATE
    public Users updateUsers(Integer id, Users users);

    //DELETE
    public void deleteUsers(Integer id);

    //DETAIL
    Optional<Users> getUsersById(Integer id);

    //SEARCH
    public Page<Users> searchUsers(String keyword, int page, int pageSize);

    //ON/OFF for enable/disable
    public void UsersStatus(Integer id);
}
