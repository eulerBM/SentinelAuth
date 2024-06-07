package com.example.authen.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureMockMvc
@SpringBootTest
class ChangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void ChangePassUrlExistTest() throws Exception {

        mockMvc.perform(post("/user/change/v1/password")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void ChangeUsernameUrlExistTest() throws Exception {

        mockMvc.perform(post("/user/change/v1/username")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void LanguageRequestUrlExistTest() throws Exception {

        mockMvc.perform(post("/user/change/v1/language")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void RoleRequestUrlExistTest() throws Exception {

        mockMvc.perform(post("/user/change/v1/role")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isUnauthorized());
    }
}