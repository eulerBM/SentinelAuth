package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoleRequestTest {

    @Test
    void RoleRequestExistTest() {

        RoleRequestDTP request = new RoleRequestDTP (

                "USER"

        );

        assertThat(request).isNotNull();

    }
}
