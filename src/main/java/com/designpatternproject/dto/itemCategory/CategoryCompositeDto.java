package com.designpatternproject.dto.itemCategory;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Value
@Getter
@Setter
@AllArgsConstructor
public class CategoryCompositeDto implements CategoryComponentDto {
    Long id;
    String category;
    Integer level;
    Long parent;
    List<CategoryComponentDto> subcategories = new ArrayList<>();
}