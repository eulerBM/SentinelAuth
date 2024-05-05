package com.example.authen.dto;

import com.example.authen.entity.UsersModel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BannedUserDTO {

    private long id;

    private String username;

    private String email;

    private String message;

    public BannedUserDTO(UsersModel entity) {

        this.id = entity.getId();

        this.username = entity.getUsername();

        this.email = entity.getEmail();

        this.message = String.format("Usuario %s com sucesso", entity.getStatusAccount().getAccountStatus());

    }

}
