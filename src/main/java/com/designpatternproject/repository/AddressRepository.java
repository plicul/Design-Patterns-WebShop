package com.designpatternproject.repository;

import com.designpatternproject.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> findFirstByUser_IdOrderByIdDesc(Long id);
}