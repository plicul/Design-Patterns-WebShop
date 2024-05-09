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
@Table(name = "country")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "country_name", length = 50)
    private String countryName;

    @OneToMany(mappedBy = "country")
    private Set<Address> addresses = new LinkedHashSet<>();

}