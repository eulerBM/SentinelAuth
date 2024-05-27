package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserRequestTest {

    @Test
    void LanguageRequestExistTest() {

        LoginUserRequestDTP request = new LoginUserRequestDTP (
                "teste@gmail.com",
                "teste15"
        );

        assertThat(request).isNotNull();

    }
}
