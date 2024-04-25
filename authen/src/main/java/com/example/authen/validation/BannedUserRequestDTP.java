package com.example.authen.validation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record BannedUserRequestDTP(

        @NotNull
        @Size(max = 100, message = "O tamanho deve estar entre 1 e 100 caracteres")
        String username,

        @Size(max = 8, message = "O tamanho deve estar entre 1 e 8 caracteres")
        @Pattern(regexp = "ativo|suspenso|banido", message = "A situação da conta deve ser 'ativo', 'suspenso' ou 'banido'")
        String accountStatus,

        @NotBlank
        @NotNull
        @Size(min = 3, max = 300, message = "O tamanho deve estar entre 1 e 300 caracteres")
        String reason
) {
}
