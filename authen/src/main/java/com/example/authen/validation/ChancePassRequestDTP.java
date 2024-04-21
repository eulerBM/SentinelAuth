package com.example.authen.validation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public record ChancePassRequestDTP (

        @Getter
        @NotBlank(message = "O E-mail nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho do username deve estar entre 1 e 150 caracteres")
        @Email(message = "E-mail invalido")
        String email,

        @Getter
        @NotBlank(message = "A senha antiga nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho da senha deve estar entre 1 e 150 caracteres")
        String senhaOld,

        @Getter
        @NotBlank(message = "A senha nova priemira nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho da senha deve estar entre 1 e 150 caracteres")
        String senhaNew1,


        @Getter
        @NotBlank(message = "A senha nova segunda nao pode ser em branco.")
        @Size(min = 1, max = 150, message = "O tamanho da senha deve estar entre 1 e 150 caracteres")
        String senhaNew2

){

}
