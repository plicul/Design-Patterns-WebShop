package com.designpatternproject.repository;

import com.designpatternproject.entity.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostalCodeRepository extends JpaRepository<PostalCode, Long> {
}