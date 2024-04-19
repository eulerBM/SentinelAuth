package com.example.authen.model;
import com.example.authen.validation.CreateUserRequestDTP;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;


@Table(name = "users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsersModel {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, unique = true, nullable = false)
    private  String username;

    @Column(length = 150, unique = true, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String senha;

    @Column(length = 300, nullable = false)
    private LocalDateTime create_account;

    @Column(length = 1)
    private int login_attempts;

    @Column(length = 100, nullable = true)
    private LocalDateTime last_access;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus account_status;

    public UsersModel(CreateUserRequestDTP data){
        this.username = data.username();
        this.email = data.email();
        this.senha = data.senha();
        this.create_account = LocalDateTime.now();
        this.login_attempts = 0;
        this.last_access = null;
        this.account_status = AccountStatus.ATIVO;
    }

    public enum AccountStatus {
        ATIVO,
        PENDENTE,
        INATIVO
    }

}
