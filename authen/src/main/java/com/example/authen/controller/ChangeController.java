package com.example.authen.controller;

import com.example.authen.model.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.ChancePassRequestDTP;
import com.example.authen.validation.ChangeUsernameDTP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/change")
public class ChangeController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/password")
    public ResponseEntity<String> ChangePassword(@Valid @RequestBody ChancePassRequestDTP data){

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

    @PostMapping("/username")
    public ResponseEntity ChangeUsername (@RequestBody ChangeUsernameDTP changeUsernameDTP){

        Optional<UsersModel> usernameAtual = repository.findByUsername(changeUsernameDTP.usernameAtual());
        Optional<UsersModel> usernameNovo = repository.findByUsername(changeUsernameDTP.usernameNovo());

        if (usernameAtual.isPresent()){

            if (usernameNovo.isPresent()){
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username ja esta sendo utilizado");

            } else {

                UsersModel user = usernameAtual.get();

                user.setUsername(changeUsernameDTP.usernameNovo());

                repository.save(user);

                return ResponseEntity.status(HttpStatus.OK).body("Username foi trocado com sucesso");

            }

        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario com esse username não existe");

        }
    }
}
