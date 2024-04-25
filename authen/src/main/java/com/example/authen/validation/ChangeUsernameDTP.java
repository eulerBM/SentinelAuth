package com.example.authen.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ChangeUsernameDTP(

        @NotBlank(message = "O Username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 100 caracteres")
        String usernameAtual,

        @NotBlank(message = "O Username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 100 caracteres")
        String usernameNovo

) {
}
