package com.example.authen.validation;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;


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

        @Positive(message = "O numero deve ser positivo.")
        @Size(max = 1, message = "Permitido 1 caracter")
        int login_attempts,

        @NotBlank(message = "O status da conta não pode ser em branco")
        @Pattern(regexp = "ATIVO|PENDENTE|INATIVO", message = "O status da conta deve ser 'ATIVO', 'INATIVO' ou 'PENDENTE'")
        String account_status) {


    }


