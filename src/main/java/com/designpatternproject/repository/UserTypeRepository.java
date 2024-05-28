package com.designpatternproject.repository;

import com.designpatternproject.entity.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType, Long> {
    UserType findByAccountType(String accountType);
}