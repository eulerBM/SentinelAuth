package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.PermissionRequestDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ChangePermissionService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> ChangePermission(@RequestBody PermissionRequestDTP permissionRequestDTP){

        Optional<UsersModel> userName = repository.findByUsername(permissionRequestDTP.username());

        if (userName.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

        } else {

            UsersModel user = userName.get();

            user.setPermission(UsersModel.Permission.valueOf(permissionRequestDTP.permission()));

            repository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("Permission alterada");
        }

    }
}
