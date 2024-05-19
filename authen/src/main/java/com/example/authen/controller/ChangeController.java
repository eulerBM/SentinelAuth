package com.example.authen.controller;

import com.example.authen.service.*;
import com.example.authen.validation.ChancePassRequestDTP;
import com.example.authen.validation.ChangeUsernameDTP;
import com.example.authen.validation.LanguageRequestDTP;
import com.example.authen.validation.RoleRequestDTP;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user/change/v1/")
public class ChangeController {

    @Autowired
    private ChangePasswordService changePasswordService;

    @Autowired
    private ChangeUsernameService usernameService;

    @Autowired
    private ChangeRoleService changeRoleService;

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

    @PostMapping("/role")
    public  ResponseEntity<String> ChangeRole(@Valid @RequestBody RoleRequestDTP data){

        return changeRoleService.ChangeRole(data);

    }

    @PostMapping("/language")
    public ResponseEntity<String> ChangeLanguage(@Valid @RequestBody LanguageRequestDTP data, JwtAuthenticationToken token) {

        return changeLanguageService.ChangeLanguageService(data, token);

    }
}
