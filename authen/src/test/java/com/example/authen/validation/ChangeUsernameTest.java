package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class ChangeUsernameTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void changeUsernameExistTest() {

        ChangeUsernameDTP request = new ChangeUsernameDTP(

                "teste10",
                "teste15"

        );

        assertThat(request).isNotNull();

    }

    @Test
    void changeUsernameFildsExistTest() {

        String usernameAtual = "teste10";
        String usernameNovo = "teste15";

        ChangeUsernameDTP request = new ChangeUsernameDTP(

                usernameAtual,
                usernameNovo

        );

        assertThat(request.usernameAtual()).isEqualTo(usernameAtual);
        assertThat(request.usernameNovo()).isEqualTo(usernameNovo);

    }

}
