package com.example.authen.controller;

import com.example.authen.dto.BannedUserDTO;
import com.example.authen.dto.InfosUserDTO;
import com.example.authen.service.*;
import com.example.authen.validation.BannedUserRequestDTP;
import com.example.authen.validation.CreateUserRequestDTP;
import com.example.authen.validation.LoginUserRequestDTP;
import com.example.authen.response.LoginResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<LoginResponse> LoginUser(@Valid @RequestBody LoginUserRequestDTP data) {

        return loginService.LoginUserService(data);

    }

    @PutMapping("/banned")
    public ResponseEntity<BannedUserDTO> BannedUser(@Valid @RequestBody BannedUserRequestDTP data){

        return bannedUserService.BannedUserService(data);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteUser(@PathVariable @Min(1) Long id) {

        return deleteUserService.DeleteUserService(id);

    }

}
