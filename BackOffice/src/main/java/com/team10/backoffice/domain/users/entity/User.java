package com.team10.backoffice.domain.users.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String introduce;

    @ElementCollection
    @CollectionTable(name = "oldPasswords", joinColumns = @JoinColumn(name = "user_id"))
    private List<String> oldPasswords = new ArrayList<>();

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private Long kakaoId;

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String username, String encodePassword, String email, UserRoleEnum userRoleEnum, Long kakaoId) {
        this.username = username;
        this.password = encodePassword;
        this.email = email;
        this.role = userRoleEnum;
        this.kakaoId = kakaoId;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;

        return this;
    }
}

