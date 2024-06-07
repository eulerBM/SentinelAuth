package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUserTest {

    @Autowired
    private MockMvc mockMvc;

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
