package com.example.authen.validation;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RoleRequestDTP(

        @Size(max = 6, message = "O tamanho deve estar entre 1 e 6 caracteres")
        @Pattern(regexp = "ADMIN|USER", message = "O status da conta deve ser 'ADMIN' ou 'USER'")
        String permission

) {
}
