package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class CreateUserTest {

    @Autowired
    private MockMvc mockMvc;

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
