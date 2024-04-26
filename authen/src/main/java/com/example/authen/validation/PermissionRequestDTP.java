package com.example.authen.validation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PermissionRequestDTP(

        @NotBlank(message = "O username nao pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 50 caracteres")
        String username,

        @Size(max = 13, message = "O tamanho deve estar entre 1 e 13 caracteres")
        @Pattern(regexp = "usuario|moderador|administrador", message = "O status da conta deve ser 'usuario', 'moderador' ou 'administrador'")
        String permission
) {
}
