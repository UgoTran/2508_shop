package com.t3h.eshop.storage.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

//dùng để hứng dữ liệu tạo mới User từ Browser/Client gửi lên API
//Browser gửi JSON tới Controller (hứng vào UserCreateDTO) --> Service (nhận UserDTO)
//chuyển thành UserInfo(Entity) --> Repository(nhận UserInfo) --> database
public class UserCreateRequest {
    String username;
    String password; //Dữ liệu raw khi tạo mới tài khoản user sau này sẽ được hash để lưu vào database
    String name;
    String email;
    String phoneNumber;
    Integer isBan;
    String image;
}
