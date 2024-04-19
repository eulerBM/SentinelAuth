package com.example.authen.validation;
import com.example.authen.model.UsersModel;
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

        @Size(max = 8, message = "O tamanho deve estar entre 1 e 7 caracteres")
        @Pattern(regexp = "ativo|pendente|inativo", message = "O status da conta deve ser 'ativo', 'pendente' ou 'inativo'")
        String account_status,

        @Size(max = 13, message = "O tamanho deve estar entre 1 e 13 caracteres")
        @Pattern(regexp = "usuario|moderador|administrador", message = "O status da conta deve ser 'usuario', 'moderador' ou 'administrador'")
        String permission,

        @Size(max = 9, message = "O tamanho deve estar entre 1 e 9 caracteres")
        @Pattern(regexp = "mandarim|espanhol|ingles|portugues|arabe", message = "A linguagem da conta deve ser 'mandarim', 'espanhol', 'ingles', 'portugues' ou 'arabe'")
        String language) {

    }


