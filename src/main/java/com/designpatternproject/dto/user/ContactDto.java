package com.designpatternproject.dto.user;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link com.designpatternproject.entity.Contact}
 */
@Data
@Getter
@Setter
@NoArgsConstructor(force = true)
public class ContactDto implements Serializable {
    Long id;
    String phoneNumber;
    String email;
    String firstName;
    String lastName;
    LocalDate creationDate;
}