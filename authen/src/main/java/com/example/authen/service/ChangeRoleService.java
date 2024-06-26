package com.example.authen.service;

import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.RoleRequestDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChangeRoleService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> ChangeRole(@RequestBody RoleRequestDTP roleRequestDTP, JwtAuthenticationToken token){

        Optional<UsersModel> userName = repository.findByIdPublic(UUID.fromString(token.getName()));

        if (userName.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

        } else {

            UsersModel user = userName.get();

            user.getStatusAccount().setRole(StatusAccount.ChoiceRole.valueOf(roleRequestDTP.permission()));

            repository.save(user);

            String msg = String.format("Role do %s alterada para %s", user.getUsername(), roleRequestDTP.permission());

            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }
    }
}
