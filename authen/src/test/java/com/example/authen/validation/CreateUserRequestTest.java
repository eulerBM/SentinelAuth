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
}
