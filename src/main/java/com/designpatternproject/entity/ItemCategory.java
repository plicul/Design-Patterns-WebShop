package com.designpatternproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "item_category")
public class ItemCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "category", nullable = false, length = 50)
    private String category;

    @OneToMany(mappedBy = "itemCategory")
    private Set<Item> items = new LinkedHashSet<>();

    @NotNull
    @Column(name = "level", nullable = false)
    private Integer level;

    @Column(name = "parent")
    private Long parent;

}