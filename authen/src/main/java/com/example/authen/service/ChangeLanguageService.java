package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.LanguageRequestDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ChangeLanguageService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> ChangeLanguageService(@RequestBody LanguageRequestDTP languageRequestDTP){

        Optional<UsersModel> userName = repository.findByUsername(languageRequestDTP.username());

        if (userName.isEmpty()){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

        }
        else {

            UsersModel user = userName.get();

            user.setLanguage(UsersModel.Language.valueOf(languageRequestDTP.language()));

            repository.save(user);

            return ResponseEntity.status(HttpStatus.OK).body("linguagem da conta alterada");

        }
    }
}
