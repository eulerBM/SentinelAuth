package com.example.authen.controller;

import com.example.authen.model.StatusAccount;
import com.example.authen.model.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.service.LoginUser;
import com.example.authen.validation.BannedUserRequestDTP;
import com.example.authen.validation.CreateUserRequestDTP;
import com.example.authen.validation.LoginUserRequestDTP;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UsersRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Qualifier("resourceHandlerMapping")
    @Autowired
    private HandlerMapping resourceHandlerMapping;

    @Autowired
    private LoginUser loginService;

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<UsersModel>> InfosUser(@PathVariable @Min(1) Long id) {

        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<UsersModel> user = repository.findById(id);

        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<String> CreateUser(@Valid @RequestBody CreateUserRequestDTP data) {

        try {

            UsersModel userData = new UsersModel(data);

            repository.save(userData);

            return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");

        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro no servidor");

        }

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

            return ResponseEntity.status(HttpStatus.OK).body("Usuario logado");

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
