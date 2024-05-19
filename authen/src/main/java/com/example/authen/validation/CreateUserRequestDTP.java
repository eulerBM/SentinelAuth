package com.example.authen.validation;
import jakarta.validation.constraints.*;

public record CreateUserRequestDTP (

        @NotBlank(message = "O username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 50 caracteres")
        String username,

        @NotBlank(message = "O E-mail nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho do username deve estar entre 1 e 150 caracteres")
        @Email(message = "E-mail invalido")
        String email,

        @NotBlank(message = "A senha não pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 100 caracteres")
        String senha,

        @Size(max = 5, message = "O tamanho deve estar entre 1 e 13 caracteres")
        @Pattern(regexp = "ADMIN|USER", message = "O status da conta deve ser 'ADMIN' ou 'USER'")
        String role,

        @Size(max = 9, message = "O tamanho deve estar entre 1 e 9 caracteres")
        @Pattern(regexp = "mandarim|espanhol|ingles|arabe|portugues", message = "A linguagem da conta deve ser 'mandarim', 'espanhol', 'ingles', 'portugues' ou 'arabe'")
        String language) {

    }


