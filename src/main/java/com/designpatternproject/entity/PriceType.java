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
@Table(name = "price_type")
@AllArgsConstructor
@NoArgsConstructor
public class PriceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_type_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "price_type", nullable = false, length = 50)
    private String priceType;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "priceType")
    private Set<Price> prices = new LinkedHashSet<>();

}