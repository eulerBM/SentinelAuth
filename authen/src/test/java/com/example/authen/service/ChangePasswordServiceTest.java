package com.example.authen.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChangePasswordServiceTest {

    @Autowired
    ChangePasswordService changePasswordService;

    @Test
    void changePasswordServiceExistTest() {

        assertThat(changePasswordService).isNotNull();

    }
}