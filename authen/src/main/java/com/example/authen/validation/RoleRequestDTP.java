package com.example.authen.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RoleRequestDTP(

        @NotBlank(message = "O username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 50 caracteres")
        String username,

        @Size(max = 6, message = "O tamanho deve estar entre 1 e 6 caracteres")
        @Pattern(regexp = "ADMIN|USER", message = "O status da conta deve ser 'ADMIN' ou 'USER'")
        String permission
) {
}
