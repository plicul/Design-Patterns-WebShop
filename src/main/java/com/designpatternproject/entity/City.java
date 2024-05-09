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
@Table(name = "city")
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "city", nullable = false, length = 200)
    private String city;

    @OneToMany(mappedBy = "city")
    private Set<Address> addresses = new LinkedHashSet<>();

}