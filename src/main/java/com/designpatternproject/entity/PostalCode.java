package com.designpatternproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "postal_code")
@AllArgsConstructor
@NoArgsConstructor
public class PostalCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postal_code_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "postal_code", columnDefinition = "int UNSIGNED not null")
    private Long postalCode;

    @OneToMany(mappedBy = "postalCode")
    private Set<Address> addresses = new LinkedHashSet<>();

}