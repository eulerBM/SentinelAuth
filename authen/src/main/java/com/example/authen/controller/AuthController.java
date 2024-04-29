package com.example.authen.controller;

import com.example.authen.dto.InfosUserDTO;
import com.example.authen.entity.StatusAccount;
import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.service.*;
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
    private CreateUserService createUserService;

    @Autowired
    private GetUserService authService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private BannedUserService bannedUserService;

    @Autowired
    private DeleteUserService deleteUserService;

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

        return loginService.LoginUserService(data);

    }

    @PutMapping("/banned")
    public ResponseEntity<String> BannedUser(@Valid @RequestBody BannedUserRequestDTP data){

        return bannedUserService.BannedUserService(data);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable @Min(1) Long id) {

        return deleteUserService.DeleteUserService(id);
    }

}
