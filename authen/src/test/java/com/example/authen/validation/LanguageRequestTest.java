package com.example.authen.validation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LanguageRequestTest {

    @Test
    void LanguageRequestExistTest() {

        LanguageRequestDTP request = new LanguageRequestDTP(
                "espanhol"
        );

        assertThat(request).isNotNull();

    }
}
