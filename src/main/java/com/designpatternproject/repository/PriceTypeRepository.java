package com.designpatternproject.repository;

import com.designpatternproject.entity.PriceType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceTypeRepository extends JpaRepository<PriceType, Long> {
}