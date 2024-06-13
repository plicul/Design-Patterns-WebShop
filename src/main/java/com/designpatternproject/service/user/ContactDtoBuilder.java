package com.designpatternproject.service.user;

import com.designpatternproject.dto.user.ContactDto;

import java.time.LocalDate;

public class ContactDtoBuilder {
    private ContactDto contactDto;

    public ContactDtoBuilder() {
        this.contactDto = new ContactDto();
    }

    public ContactDtoBuilder withId(Long id) {
        this.contactDto.setId(id);
        return this;
    }

    public ContactDtoBuilder withPhoneNumber(String phoneNumber) {
        this.contactDto.setPhoneNumber(phoneNumber);
        return this;
    }

    public ContactDtoBuilder withEmail(String email) {
        this.contactDto.setEmail(email);
        return this;
    }

    public ContactDtoBuilder withFirstName(String firstName) {
        this.contactDto.setFirstName(firstName);
        return this;
    }

    public ContactDtoBuilder withLastName(String lastName) {
        this.contactDto.setLastName(lastName);
        return this;
    }

    public ContactDtoBuilder withCreationDate(LocalDate creationDate) {
        this.contactDto.setCreationDate(creationDate);
        return this;
    }

    public ContactDto build() {
        return this.contactDto;
    }
}
