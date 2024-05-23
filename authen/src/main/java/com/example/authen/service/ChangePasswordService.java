package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.ChancePassRequestDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangePasswordService {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<String> ChangePasswordService(ChancePassRequestDTP data, JwtAuthenticationToken token){

        Optional<UsersModel> emailUser = repository.findByEmail(data.getEmail());



        if (emailUser.isEmpty()){

            return ResponseEntity.status((HttpStatus.NOT_FOUND)).body("E-mail não encontrado!");

        }

        UsersModel user = emailUser.get();
        String hashedPassword = user.getSenha();
        boolean passwordMatches = passwordEncoder.matches(data.getSenhaOld(), hashedPassword);

        if ( user.getIdPrivate() != Long.parseLong(token.getName())){

            return ResponseEntity.status((HttpStatus.CONFLICT)).body("Usuario do token não condiz com o informado");

        }

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
