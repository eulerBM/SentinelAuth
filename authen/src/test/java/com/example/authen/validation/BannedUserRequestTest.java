package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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

    @Test
    void bannedUserRequestFildsExistTest() {

        String username = "usertest";
        String accountStatus = "ativo";
        String reason = "teste1teste1tste1";

        BannedUserRequestDTP request = new BannedUserRequestDTP(

                username,
                accountStatus,
                reason

        );

        assertThat(request.username()).isEqualTo(username);
        assertThat(request.accountStatus()).isEqualTo(accountStatus);
        assertThat(request.reason()).isEqualTo(reason);

    }

}


