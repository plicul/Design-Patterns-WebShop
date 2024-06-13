package com.designpatternproject.dto.user;

import lombok.Value;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.designpatternproject.entity.UserType}
 */
@Value
public class UserTypeDto implements Serializable {
    Long id;
    String accountType;
    LocalDate creationDate;
}
