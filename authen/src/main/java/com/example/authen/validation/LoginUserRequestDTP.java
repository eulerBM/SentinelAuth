package com.example.authen.validation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record LoginUserRequestDTP(

        @Getter
        @NotBlank(message = "O E-mail nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho do username deve estar entre 1 e 150 caracteres")
        @Email(message = "E-mail invalido")
        String email,

        @Getter
        @NotBlank(message = "A senha não pode ser em branco.")
        @Size(min = 1, max = 100, message = "O tamanho do username deve estar entre 1 e 100 caracteres")
        String senha
){


}

