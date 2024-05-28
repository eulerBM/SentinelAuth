package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeUsernameTest {

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
