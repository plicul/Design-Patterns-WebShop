package com.designpatternproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "credit_card", indexes = {
        @Index(name = "credit_card_fk2", columnList = "credit_card_type")
})
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "credit_card_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "credit_card_number", nullable = false, length = 16)
    private String creditCardNumber;

    @Column(name = "credit_card_type", nullable = false, length = 20)
    private String creditCardType;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cvv", length = 6)
    private String cvv;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

}