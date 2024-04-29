package com.example.authen.dto;

import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BannedUserDTO {

    private long id;

    private String username;

    private String email;

    public BannedUserDTO(UsersModel entity) {

        this.id = entity.getId();

        this.username = entity.getUsername();

        this.email = entity.getEmail();

    }
}
