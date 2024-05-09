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
@Table(name = "item")
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", columnDefinition = "int UNSIGNED not null")
    private Long id;

    @Column(name = "item_title", nullable = false)
    private String itemTitle;

    @Column(name = "item_description")
    private String itemDescription;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_category_id", nullable = false)
    private ItemCategory itemCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_subcategory_id")
    private ItemSubcategory itemSubcategory;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "item")
    private Set<Price> prices = new LinkedHashSet<>();

    @OneToMany(mappedBy = "item")
    private Set<TransactionItem> transactionItems = new LinkedHashSet<>();

}