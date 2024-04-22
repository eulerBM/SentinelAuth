package com.example.authen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "StatusAccount")
@Entity(name = "StatusAccount")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private LocalDateTime time_banned;

    @Column(length = 50)
    private AccountStatus accountStatus;

    public StatusAccount (){
        this.time_banned = null;
        this.accountStatus = AccountStatus.ativo;

    }

    public enum AccountStatus {
        ativo,
        pendente,
        inativo
    }
    
    
}

