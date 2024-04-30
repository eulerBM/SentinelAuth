package com.example.authen.controller;

import com.example.authen.entity.UsersModel;
import com.example.authen.repositorys.UsersRepository;
import com.example.authen.service.*;
import com.example.authen.validation.ChancePassRequestDTP;
import com.example.authen.validation.ChangeUsernameDTP;
import com.example.authen.validation.LanguageRequestDTP;
import com.example.authen.validation.PermissionRequestDTP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user/change/v1/")
public class ChangeController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private ChangeUsernameService usernameService;

    @Autowired
    private ChangePermissionService changePermissionService;

    @Autowired
    private ChangeLanguageService changeLanguageService;

    @PostMapping("/password")
    public ResponseEntity<String> ChangePassword(@Valid @RequestBody ChancePassRequestDTP data){

        return changePasswordService.ChangePasswordService(data);

    }

    @PostMapping("/username")
    public ResponseEntity<String> ChangeUsername (@Valid @RequestBody ChangeUsernameDTP data){

        return usernameService.ChangeUsernameService(data);

    }

    @PostMapping("/permission")
    public  ResponseEntity<String> ChangePermission(@Valid @RequestBody PermissionRequestDTP data){

        return changePermissionService.ChangePermission(data);

    }

    @PostMapping("/language")
    public ResponseEntity<String> ChangeLanguage(@Valid @RequestBody LanguageRequestDTP data) {

        return changeLanguageService.ChangeLanguageService(data);

    }
}
