package com.t3h.eshop.storage.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "users_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "platform", length = 20)
    private Platform platform;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "email", nullable = false, length = 100, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false, unique = true, length = 15)
    private String phoneNumber;

    @Column(name = "image", columnDefinition = "LONGTEXT")
    @JsonIgnore //Khi trả về JSON, sẽ bỏ qua field này
    private String image;

    @Column(name = "created_at", updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "status")
    private Boolean status;
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users user = (Users) o;
        return userId != null && userId.equals(user.getUserId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
