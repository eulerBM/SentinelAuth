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
}
