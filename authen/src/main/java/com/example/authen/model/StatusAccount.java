package com.example.authen.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "StatusAccount")
@Entity(name = "StatusAccount")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@EqualsAndHashCode(of = "id")
public class StatusAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "users_id")
    private UsersModel user;

    @Column(length = 50)
    private String accountStatus;

    @Column(length = 50)
    private LocalDateTime time_banned;

    @Column(length = 300)
    private String reason;
    
}

