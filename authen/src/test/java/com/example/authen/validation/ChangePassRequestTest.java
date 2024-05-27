package com.example.authen.validation;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;


public class ChangePassRequestTest {

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

}
