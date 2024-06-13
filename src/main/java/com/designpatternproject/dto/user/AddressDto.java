package com.designpatternproject.dto.user;

import lombok.Data;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.designpatternproject.entity.Address}
 */
@Data
public class AddressDto implements Serializable {
    Long id;
    String streetAddress;
    String city;
    Long postalCode;
    String country;
    LocalDate creationDate;
}