package com.example.authen.model;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.authen.validation.CreateUserRequestDTP;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Table(name = "users")
@Entity(name = "users")
@Getter
@Setter
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

    @Setter
    @Column(length = 150, nullable = false)
    private String senha;

    @Column(length = 50, nullable = false)
    private LocalDateTime create_account;

    @Setter
    @Column(length = 1)
    private int login_attempts;

    @Setter
    @Column(length = 50)
    private LocalDateTime last_access;

    @Column(length = 13, nullable = false)
    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Column(length = 9, nullable = false)
    @Enumerated(EnumType.STRING)
    private Language language;

    @Setter
    @Getter
    @OneToOne(cascade = CascadeType.ALL)
    private StatusAccount statusAccount;

    public UsersModel(CreateUserRequestDTP data){

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.username = data.username();
        this.email = data.email();
        this.senha = passwordEncoder.encode(data.senha());
        this.create_account = LocalDateTime.now();
        this.login_attempts = 0;
        this.last_access = null;
        this.permission = Permission.usuario;
        this.language = Language.portugues;

        StatusAccount statusAccount = new StatusAccount();
        statusAccount.setAccountStatus(StatusAccount.ChoiceStatus.valueOf("ativo"));
        statusAccount.setTime_banned(null);
        statusAccount.setReason(null);

        this.statusAccount = statusAccount;
    }

    public enum Permission {
        usuario,
        moderador,
        administrador
    }

    public enum Language {
        mandarim,
        espanhol,
        ingles,
        portugues,
        arabe
    }

}
