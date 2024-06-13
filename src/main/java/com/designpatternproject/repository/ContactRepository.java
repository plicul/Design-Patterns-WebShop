package com.designpatternproject.repository;

import com.designpatternproject.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findFirstByUser_IdOrderByIdDesc(Long id);
}