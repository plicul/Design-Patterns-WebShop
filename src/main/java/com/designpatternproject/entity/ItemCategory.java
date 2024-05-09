package com.designpatternproject.entity;

import jakarta.persistence.*;
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
    @Column(name = "item_category_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "item_category", nullable = false, length = 50)
    private String itemCategory;

    @OneToMany(mappedBy = "itemCategory")
    private Set<Item> items = new LinkedHashSet<>();

    @OneToMany(mappedBy = "category")
    private Set<ItemSubcategory> itemSubcategories = new LinkedHashSet<>();

}