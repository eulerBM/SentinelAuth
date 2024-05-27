package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BannedUserRequestTest {

    @Test
    void bannedUserRequestExistTest() {

        BannedUserRequestDTP request = new BannedUserRequestDTP(

                "usertest",
                "ativo",
                "teste1teste1tste1"

        );

        assertThat(request).isNotNull();

    }
}
