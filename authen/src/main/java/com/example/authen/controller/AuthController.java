package com.example.authen.controller;

import com.example.authen.dto.InfosUserDTO;
import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.service.GetUserService;
import com.example.authen.service.CreateUserService;
import com.example.authen.validation.BannedUserRequestDTP;
import com.example.authen.validation.CreateUserRequestDTP;
import com.example.authen.validation.LoginUserRequestDTP;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/user/v1/")
public class AuthController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private GetUserService loginService;

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private GetUserService authService;

    @GetMapping("/get/{id}")
    public ResponseEntity<InfosUserDTO> InfosUser(@PathVariable @Min(1) Long id) {

        return authService.InfosUserService(id);

    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateUser(@Valid @RequestBody CreateUserRequestDTP data) {

        return createUserService.CreateUserService(data);

    }

    @PostMapping("/login")
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

    @PutMapping("/banned")
    public ResponseEntity<String> BannedUser(@RequestBody BannedUserRequestDTP bannedUserRequestDTP){

        Optional<UsersModel> user = repository.findByUsername(bannedUserRequestDTP.username());

        if (user.isEmpty()){

            return ResponseEntity.notFound().build();

        } else {

            UsersModel userModel = user.get();
            StatusAccount userStatus = userModel.getStatusAccount();

            userStatus.setAccountStatus(StatusAccount.ChoiceStatus.valueOf(bannedUserRequestDTP.accountStatus()));
            userStatus.setTime_banned(LocalDateTime.now());
            userStatus.setReason(bannedUserRequestDTP.reason());

            repository.save(userModel);

            String msg = String.format("O usuario %s foi %s ", bannedUserRequestDTP.username(), bannedUserRequestDTP.accountStatus());

            return ResponseEntity.status(HttpStatus.OK).body(msg);

        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable Long id){

        Optional<UsersModel> findUserId = repository.findById(id);

        if (findUserId.isEmpty()){

            return ResponseEntity.notFound().build();

        }

        else {

            repository.deleteById(id);

            return ResponseEntity.noContent().build();

        }

    }

}
