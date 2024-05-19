package com.example.authen.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LanguageRequestDTP(

        @Size(max = 9, message = "O tamanho deve estar entre 1 e 9 caracteres")
        @Pattern(regexp = "mandarim|espanhol|ingles|arabe|portugues", message = "A linguagem da conta deve ser 'mandarim', 'espanhol', 'ingles', 'portugues' ou 'arabe'")
        String language
) {
}
