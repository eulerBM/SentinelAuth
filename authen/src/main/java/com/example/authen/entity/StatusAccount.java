package com.example.authen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "StatusAccount")
@Entity
@Setter
@Getter
@EqualsAndHashCode(of = "id")
public class StatusAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 8)
    private ChoiceStatus accountStatus;

    @Column(length = 50)
    private LocalDateTime time_banned;

    @Column(length = 300)
    private String reason;

    public enum ChoiceStatus{
        ativo,
        suspenso,
        banido,

    }
    
}

