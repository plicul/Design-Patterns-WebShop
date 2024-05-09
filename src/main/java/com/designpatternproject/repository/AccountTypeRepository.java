package com.designpatternproject.repository;

import com.designpatternproject.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Long> {
}