package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.LoginUserRequestDTP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {
    
    @Autowired
    private UsersRepository repository;

    public ResponseEntity<String> LoginUser(@Valid @RequestBody LoginUserRequestDTP data) {

        Optional<UsersModel> emailUser = repository.findByEmail(data.email());

        if (emailUser.isEmpty()) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

        }

        UsersModel getUserForAttemp = emailUser.get();
        int getAttemps = getUserForAttemp.getLogin_attempts();

        boolean userBannedOrSus = loginService.UserBanned(getUserForAttemp);

        if (userBannedOrSus) {

            String msg = String.format("Essa conta está sobe restrição !");

            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);

        }

        if (getAttemps >= 5) {

            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Muitas tentativas");

        }

        UsersModel user = emailUser.get();
        String hashedPassword = user.getSenha();

        boolean passwordMatches = passwordEncoder.matches(data.getSenha(), hashedPassword);

        if (passwordMatches) {

            user.setLast_access(LocalDateTime.now());
            repository.save(user);

            var now = Instant.now();
            var expiresIn = 300L;

            var claims = JwtClaimsSet.builder()
                    .issuer("sentinelauth")
                    .subject(user.getUsername())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.status(HttpStatus.OK).body(jwtValue);

        }

        UsersModel getUser = emailUser.get();

        getUser.setLogin_attempts(getUser.getLogin_attempts() + 1);

        repository.save(getUser);

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não existe");

    }
}
