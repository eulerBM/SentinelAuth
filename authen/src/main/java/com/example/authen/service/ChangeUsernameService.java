package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.ChangeUsernameDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChangeUsernameService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> ChangeUsernameService (ChangeUsernameDTP data){

        Optional<UsersModel> usernameAtual = repository.findByUsername(data.usernameAtual());
        Optional<UsersModel> usernameNovo = repository.findByUsername(data.usernameNovo());

        if (usernameAtual.isPresent()){

            if (usernameNovo.isPresent()){

                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username ja esta sendo utilizado");

            } else {

                UsersModel user = usernameAtual.get();

                user.setUsername(data.usernameNovo());

                repository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body("Username foi trocado com sucesso");

            }

        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario com esse username não existe");

        }

    }
}
