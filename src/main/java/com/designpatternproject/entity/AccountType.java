package com.designpatternproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "account_type")
@AllArgsConstructor
@NoArgsConstructor
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "account_type", nullable = false, length = 30)
    private String accountType;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "accountType")
    private Set<Account> accounts = new LinkedHashSet<>();

}