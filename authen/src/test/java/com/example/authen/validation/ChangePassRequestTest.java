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
