package com.designpatternproject.service.user;

import com.designpatternproject.dto.user.AddressDto;
import com.designpatternproject.entity.City;
import com.designpatternproject.entity.Country;
import com.designpatternproject.entity.PostalCode;

import java.time.LocalDate;

public class AddressDtoBuilder {
    private AddressDto addressDto;

    public AddressDtoBuilder() {
        this.addressDto = new AddressDto();
    }

    public AddressDtoBuilder withId(Long id) {
        this.addressDto.setId(id);
        return this;
    }

    public AddressDtoBuilder withStreetAddress(String streetAddress) {
        this.addressDto.setStreetAddress(streetAddress);
        return this;
    }

    public AddressDtoBuilder withCity(String city) {
        this.addressDto.setCity(city);
        return this;
    }

    public AddressDtoBuilder withPostalCode(Long postalCode) {
        this.addressDto.setPostalCode(postalCode);
        return this;
    }

    public AddressDtoBuilder withCountry(String country) {
        this.addressDto.setCountry(country);
        return this;
    }

    public AddressDtoBuilder withCreationDate(LocalDate creationDate) {
        this.addressDto.setCreationDate(creationDate);
        return this;
    }

    public AddressDto build() {
        return this.addressDto;
    }
}
