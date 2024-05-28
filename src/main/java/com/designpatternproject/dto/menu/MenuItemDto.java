package com.designpatternproject.dto.menu;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.designpatternproject.entity.MenuItem}
 */
@AllArgsConstructor
@Value
public class MenuItemDto implements Serializable {
    @NotNull
    @Size(max = 64)
    String name;
    @NotNull
    @Size(max = 64)
    String routePath;
}