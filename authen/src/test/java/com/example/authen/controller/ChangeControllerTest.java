package com.example.authen.controller;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.authen.service.ChangeLanguageService;
import com.example.authen.service.ChangeRoleService;
import com.example.authen.service.ChangeUsernameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class ChangeControllerTest {

    @Autowired
    private ChangeController changeController;

    @Test
    void changePassword() {

        assertThat(changeController).isNotNull();

    }

    @Test
    void changeUsername() {

        assertThat(changeController).isNotNull();

    }

    @Test
    void changeRole() {

        assertThat(changeController).isNotNull();

    }

    @Test
    void changeLanguage() {

        assertThat(changeController).isNotNull();

    }
}