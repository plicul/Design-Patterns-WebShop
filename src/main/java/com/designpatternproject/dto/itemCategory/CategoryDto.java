package com.designpatternproject.dto.itemCategory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO for {@link com.designpatternproject.entity.ItemCategory}
 */
@Value
@Getter
@Setter
@AllArgsConstructor
public class CategoryDto implements Serializable {
    Long id;
    String category;
    Integer level;
    Long parent;
    List<CategoryDto> subcategories = new ArrayList<>();

}