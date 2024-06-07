package com.example.authen.dto;

import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LoginUserDTO {

    private long id;

    private String username;

    private String email;

    private LocalDateTime create_account;

    private int login_attempts;

    private LocalDateTime last_access;

    private String language;

    private StatusAccount statusAccount;

    public LoginUserDTO(UsersModel entity) {

        this.id = entity.getIdPrivate();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.create_account = entity.getCreate_account();
        this.login_attempts = entity.getLogin_attempts();
        this.last_access = entity.getLast_access();
        this.language = String.valueOf(entity.getLanguage());
        this.statusAccount = entity.getStatusAccount();

    }
}
