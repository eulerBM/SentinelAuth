package com.example.authen.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;


import org.junit.jupiter.api.Test;

@AutoConfigureMockMvc
@SpringBootTest
public class ChangePasswordTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void changePasswordExistTest() {

        ChangePassRequestDTP request = new ChangePassRequestDTP(

                "emailTeste@gmail.com",
                "teste15",
                "teste16",
                "teste16"

        );

        assertThat(request).isNotNull();

    }

    @Test
    void changePassRequestFildsExistTest() {

        String email = "emailTeste@gmail.com";
        String senhaOld= "teste15";
        String senhaNew1 = "teste16";
        String senhaNew2 = "teste16";

        ChangePassRequestDTP request = new ChangePassRequestDTP(

                email,
                senhaOld,
                senhaNew1,
                senhaNew2

        );

        assertThat(request.email()).isEqualTo(email);
        assertThat(request.senhaOld()).isEqualTo(senhaOld);
        assertThat(request.senhaNew1()).isEqualTo(senhaNew1);
        assertThat(request.senhaNew2()).isEqualTo(senhaNew2);

    }

}
