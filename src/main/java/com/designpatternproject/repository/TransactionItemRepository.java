package com.designpatternproject.repository;

import com.designpatternproject.entity.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}