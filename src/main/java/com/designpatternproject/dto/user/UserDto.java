package com.designpatternproject.dto.user;

import lombok.*;

import java.io.Serializable;

/**
 * DTO for {@link com.designpatternproject.entity.User}
 */
@Data
@Getter
@Setter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class UserDto implements Serializable {
    Long id;
    UserTypeDto type;
    AddressDto address;
    ContactDto contact;
    String name;
    String password;
    Float cash;


}