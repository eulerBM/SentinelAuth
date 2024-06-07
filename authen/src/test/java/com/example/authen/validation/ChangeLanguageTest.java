package com.example.authen.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeLanguageTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void LanguageRequestExistTest() {

        LanguageRequestDTP request = new LanguageRequestDTP(
                "espanhol"
        );

        assertThat(request).isNotNull();

    }

    @Test
    void LanguageRequestFildsExistTest() {

        String language = "espanhol";


        LanguageRequestDTP request = new LanguageRequestDTP(

                language

        );

        assertThat(request.language()).isEqualTo(language);

    }
}
