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
@Table(name = "item_subcategory")
@AllArgsConstructor
@NoArgsConstructor
public class ItemSubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_subcategory_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private ItemCategory category;

    @Column(name = "item_subcategory", nullable = false, length = 50)
    private String itemSubcategory;

    @OneToMany(mappedBy = "itemSubcategory")
    private Set<Item> items = new LinkedHashSet<>();

}