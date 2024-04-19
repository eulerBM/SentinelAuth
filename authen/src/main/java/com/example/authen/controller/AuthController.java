package com.example.authen.controller;
import com.example.authen.validation.CreateUserRequestDTP;
import com.example.authen.model.UsersModel;
import com.example.authen.users.UsersRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UsersRepository repository;

    @PostMapping("/create")
    public ResponseEntity<String> CreateUser (@Valid @RequestBody CreateUserRequestDTP data){

        try {
            UsersModel userData = new UsersModel(data);
            repository.save(userData);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar usuario");

        }

    }

}

