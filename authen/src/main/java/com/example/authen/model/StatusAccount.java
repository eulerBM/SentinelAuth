package com.example.authen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

