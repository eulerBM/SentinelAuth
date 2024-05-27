package com.example.authen.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

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