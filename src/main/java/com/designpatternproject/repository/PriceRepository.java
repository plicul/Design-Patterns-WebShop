package com.designpatternproject.repository;

import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    @Modifying
    @Transactional
    @Query(value = "delete from Price p where p.item.id = :item")
    void deleteByItem_Id(Long item);
}