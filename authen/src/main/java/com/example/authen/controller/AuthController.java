package com.example.authen.controller;
import com.example.authen.validation.ChancePassRequestDTP;
import com.example.authen.validation.CreateUserRequestDTP;
import com.example.authen.model.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.LoginUserRequestDTP;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/create")
    public ResponseEntity<String> CreateUser (@Valid @RequestBody CreateUserRequestDTP data){

        try {

            UsersModel userData = new UsersModel(data);

            repository.save(userData);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor");

        }

    }

    @PostMapping("/login")
    public ResponseEntity<String> LoginUser (@Valid @RequestBody LoginUserRequestDTP data){
        Optional<UsersModel> emailUser = repository.findByEmail(data.email());

        if (emailUser.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

        } else {

            UsersModel user = emailUser.get();
            String hashedPassword = user.getSenha();

            boolean passwordMatches = passwordEncoder.matches(data.getSenha(), hashedPassword);

            if (passwordMatches) {

                user.setLast_access(LocalDateTime.now());
                repository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body("Usuario logado");

            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

            }

        }

    }

    @PostMapping("/change-password")
    public ResponseEntity<String> ChancePassword(@Valid @RequestBody ChancePassRequestDTP data){

        Optional<UsersModel> emailUser = repository.findByEmail(data.getEmail());

        if (emailUser.isEmpty()){

            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("E-mail não encontrado!");

        }

        UsersModel user = emailUser.get();
        String hashedPassword = user.getSenha();
        boolean passwordMatches = passwordEncoder.matches(data.getSenhaOld(), hashedPassword);

        if (!passwordMatches){

            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Senha antiga e a informada são diferentes");

        }

        if (!data.getSenhaNew1().equals(data.getSenhaNew2())){

            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("Senhas novas diferentes!");

        }else {

            UsersModel userIns = emailUser.get();

            String newPasswordForDb = passwordEncoder.encode(data.getSenhaNew1());

            userIns.setSenha(newPasswordForDb);

            repository.save(userIns);

            return ResponseEntity.status((HttpStatus.ACCEPTED)).body("Senha atualizada com sucesso!");

        }

    }

}

