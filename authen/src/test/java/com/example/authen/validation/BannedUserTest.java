package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureMockMvc
@SpringBootTest
public class BannedUserTest {

    @Autowired
    private MockMvc mockMvc;

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


