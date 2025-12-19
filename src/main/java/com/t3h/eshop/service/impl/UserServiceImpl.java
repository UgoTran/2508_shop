package com.t3h.eshop.service.impl;

import com.t3h.eshop.service.UserService;
import com.t3h.eshop.storage.dto.UserCreateRequest;
import com.t3h.eshop.storage.dto.UserUpdateRequest;
import com.t3h.eshop.storage.dto.UserResponse;
import com.t3h.eshop.storage.entity.UserInfo;
import com.t3h.eshop.storage.repository.UserInfoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserInfoRepository userInfoRepository;
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserResponse createUser(UserCreateRequest userCreateRequest) {
        if(userInfoRepository.existsByEmailOrPhoneNumber(userCreateRequest.getEmail(), userCreateRequest.getPhoneNumber())){
            throw new RuntimeException("User phone/email exists");
        }

        //Mapping DTO(UserCreateRequest) to Entity(UserInfo)
        UserInfo userInfo = UserInfo.builder()
                .username(userCreateRequest.getUsername())
                .name(userCreateRequest.getName())
                .email(userCreateRequest.getEmail())
                .phoneNumber(userCreateRequest.getPhoneNumber())
                .image(userCreateRequest.getImage())
                .isBan(userCreateRequest.getIsBan() == null ? 0 : userCreateRequest.getIsBan())
                .createdAt(LocalDateTime.now())
                .password(passwordEncoder.encode(userCreateRequest.getPassword()))
                .build();
        UserInfo savedUser = userInfoRepository.save(userInfo);

        return mapToResponse(savedUser); //Trả về khi response để hiển thị lên browser sau khi được thêm mới
    }

    @Override
    public List<UserResponse> findAll() {
        List<UserInfo> users = userInfoRepository.findAll();
        return users.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse updateUser(Integer Id, UserUpdateRequest userUpdateRequest) {

        //Lấy userInfo trong database bằng ID đã chọn
        UserInfo userInfo = userInfoRepository.findById(Id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        //Kiểm tra trùng lặp
        //Nếu email mới nhập khác với email cũ tức đã thay đổi và email mới này lại trùng với email nào đó trong database
        if(!userInfo.getEmail().equals(userUpdateRequest.getEmail())
            && userInfoRepository.findByEmail(userUpdateRequest.getEmail()).isPresent()){
            throw new RuntimeException("Email " + userUpdateRequest.getEmail() + " already exists");
        }
        //Tương tự với số điện thoại
        if(!userInfo.getPhoneNumber().equals(userUpdateRequest.getPhoneNumber())
                && userInfoRepository.findByPhoneNumber(userUpdateRequest.getPhoneNumber()).isPresent()){
            throw new RuntimeException("PhoneNumber " + userUpdateRequest.getPhoneNumber() + " already exists");
        }

        userInfo.setName(userUpdateRequest.getName());
        userInfo.setEmail(userUpdateRequest.getEmail());
        userInfo.setPhoneNumber(userUpdateRequest.getPhoneNumber());
        userInfo.setImage(userUpdateRequest.getImage());

        if (userUpdateRequest.getIsBan() != null) {
            userInfo.setIsBan(userUpdateRequest.getIsBan());
        }

        UserInfo savedUser = userInfoRepository.save(userInfo);
        return mapToResponse(savedUser);

    }


    @Override
    @Transactional
    public void deleteUser(Integer id) {
        if(!userInfoRepository.existsById(id)){
            throw new RuntimeException("User not found");
        }
        userInfoRepository.deleteById(id);
    }

    @Override
    public UserResponse getUserById(Integer id) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return mapToResponse(userInfo);
    }

    @Override
    @Transactional
    public UserResponse toggleBanStatus(Integer id) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));


        Integer currentStatus = userInfo.getIsBan() == null ? 0 : userInfo.getIsBan();
        Integer newStatus = (currentStatus == 0) ? 1 : 0;

        userInfo.setIsBan(newStatus);

        UserInfo savedUser = userInfoRepository.save(userInfo);
        return mapToResponse(savedUser);
    }

    //Mapping Entity (UserInfo) to DTO(UserResponse) when Response
    private UserResponse mapToResponse(UserInfo userInfo) {
        return UserResponse.builder()
                .userId(userInfo.getUserId())
                .username(userInfo.getUsername())
                .name(userInfo.getName())
                .email(userInfo.getEmail())
                .phoneNumber(userInfo.getPhoneNumber())
                .image(userInfo.getImage())
                .isBan(userInfo.getIsBan())
                .createdAt(userInfo.getCreatedAt())
                .build();
    }
}
