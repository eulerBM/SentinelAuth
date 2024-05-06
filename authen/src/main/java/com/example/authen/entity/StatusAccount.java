package com.example.authen.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "StatusAccount")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class StatusAccount {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Getter
    @Column(length = 8)
    private ChoiceStatus accountStatus;

    @Setter
    @Getter
    @Column(length = 50)
    private LocalDateTime time_banned;

    @Setter
    @Getter
    @Column(length = 300)
    private String reason;

    public enum ChoiceStatus{
        ativo,
        suspenso,
        banido,

    }
    
}

