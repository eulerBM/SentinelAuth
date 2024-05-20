package com.example.authen.service;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.validation.LoginUserRequestDTP;
import com.example.authen.response.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
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

    @Autowired
    private GetUserService loginService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtEncoder jwtEncoder;

    public ResponseEntity<LoginResponse> LoginUserService(@Valid @RequestBody LoginUserRequestDTP data) {

        Optional<UsersModel> emailUser = repository.findByEmail(data.email());

        if (emailUser.isEmpty()) {

            throw new BadCredentialsException("Usuario não existe");

        }

        UsersModel getUserForAttemp = emailUser.get();
        int getAttemps = getUserForAttemp.getLogin_attempts();

        boolean userBannedOrSus = loginService.UserBanned(getUserForAttemp);

        if (userBannedOrSus) {

            String msg = "Essa conta está sobe restrição !";

            throw new BadCredentialsException(msg);

        }

        if (getAttemps >= 5) {

            throw new BadCredentialsException("Muitas tentativas");

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
                    .subject(String.valueOf(user.getId()))
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", user.getStatusAccount().getRole().name())
                    .build();

            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

        }

        UsersModel getUser = emailUser.get();

        getUser.setLogin_attempts(getUser.getLogin_attempts() + 1);

        repository.save(getUser);

        throw new BadCredentialsException("Usuario não existe");

    }

}
