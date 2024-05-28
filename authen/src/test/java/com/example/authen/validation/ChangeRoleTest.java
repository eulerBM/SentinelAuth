package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeRoleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void RoleRequestExistTest() {

        RoleRequestDTP request = new RoleRequestDTP (

                "USER"

        );

        assertThat(request).isNotNull();

    }

    @Test
    void RoleRequestFildsExistTest() {

        String permission = "USER";

        RoleRequestDTP request = new RoleRequestDTP(

                permission

        );

        assertThat(request.permission()).isEqualTo(permission);

    }

}
