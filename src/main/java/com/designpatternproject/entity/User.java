package com.designpatternproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type")
    private UserType type;

    @Column(name = "account_name", nullable = false, length = 32)
    private String name;

    @Column(name = "password", nullable = false, length = 256)
    private String password;

    @Column(name = "cash")
    private Float cash;

}