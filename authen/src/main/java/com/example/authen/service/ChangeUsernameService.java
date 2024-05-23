package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.ChangeUsernameDTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ChangeUsernameService {

    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> ChangeUsernameService (ChangeUsernameDTP data, JwtAuthenticationToken token){

        Optional<UsersModel> usernameAtual = repository.findByUsername(data.usernameAtual());
        Optional<UsersModel> usernameNovo = repository.findByUsername(data.usernameNovo());

        if (usernameAtual.isPresent()){

            if (usernameNovo.isPresent()){

                return ResponseEntity.status(HttpStatus.CONFLICT).body("Username ja esta sendo utilizado");

            } else {

                UsersModel user = usernameAtual.get();

                user.setUsername(data.usernameNovo());

                repository.save(user);


                if ( user.getIdPublic() != UUID.fromString(token.getName())){

                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario e ID nao são iguais");

                }

                return ResponseEntity.status(HttpStatus.OK).body("Username foi trocado com sucesso");

            }

        } else {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario com esse username não existe");

        }

    }
}
