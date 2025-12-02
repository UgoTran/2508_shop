package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.UsersService;
import com.t3h.eshop.storage.entity.Users;
import com.t3h.eshop.storage.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Page<Users> findAllUsers(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return usersRepository.findAll(pageable);
    }

    @Override
    public Users createUsers(Users users) {
        //Luôn set ID về null để đảm bảo Hibernate tạo mới thay vì update
        users.setUserId(null);
        return usersRepository.save(users);
    }

    @Override
    public Users updateUsers(Integer id, Users users) {
        Users existingUsers = usersRepository.findById(id).orElse(null);
        if (existingUsers != null) {
            existingUsers.setName(users.getName());
            existingUsers.setEmail(users.getEmail());
            existingUsers.setPhoneNumber(users.getPhoneNumber());
            existingUsers.setStatus(users.getStatus());
            existingUsers.setPlatform(users.getPlatform());
            return usersRepository.save(existingUsers);
        }
        return null;
    }

    @Override
    public void deleteUsers(Integer id) {
        usersRepository.deleteById(id);
    }

    @Override
    public Optional<Users> getUsersById(Integer id) {
        return usersRepository.findById(id);
    }

    @Override
    public Page<Users> searchUsers(String keyword, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return usersRepository.searchUsersByKeyword(keyword, pageable);
    }

    @Override
    public void UsersStatus(Integer id) {
        Users users = usersRepository.findById(id).orElse(null);
        users.setStatus(!users.getStatus());
        usersRepository.save(users);
    }
}
