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

    @Test
    void LanguageRequestFildsExistTest() {

        String email = "teste@gmail.com";
        String senha = "teste15";

        LoginUserRequestDTP request = new LoginUserRequestDTP(

                email,
                senha

        );

        assertThat(request.email()).isEqualTo(email);
        assertThat(request.senha()).isEqualTo(senha);

    }
}
