package com.example.authen.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LanguageRequestDTP(

        @NotBlank(message = "O username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 50 caracteres")
        String username,

        @Size(max = 9, message = "O tamanho deve estar entre 1 e 9 caracteres")
        @Pattern(regexp = "mandarim|espanhol|ingles|arabe|portugues", message = "A linguagem da conta deve ser 'mandarim', 'espanhol', 'ingles', 'portugues' ou 'arabe'")
        String language
) {
}
