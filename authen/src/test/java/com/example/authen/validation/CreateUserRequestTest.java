package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateUserRequestTest {

    @Test
    void CreateUserRequestExistTest() {

        CreateUserRequestDTP request = new CreateUserRequestDTP(

                "teste10",
                "teste@gmail.com",
                "teste15",
                "ADMIN",
                "português"


        );

        assertThat(request).isNotNull();

    }

    @Test
    void CreateUserFildsExistTest() {

        String username = "teste10";
        String email = "teste@gmail.com";
        String senha = "teste15";
        String role = "ADMIN";
        String language = "português";

        CreateUserRequestDTP request = new CreateUserRequestDTP(

                username,
                email,
                senha,
                role,
                language

        );

        assertThat(request.username()).isEqualTo(username);
        assertThat(request.email()).isEqualTo(email);
        assertThat(request.senha()).isEqualTo(senha);
        assertThat(request.role()).isEqualTo(role);
        assertThat(request.language()).isEqualTo(language);

    }
}
