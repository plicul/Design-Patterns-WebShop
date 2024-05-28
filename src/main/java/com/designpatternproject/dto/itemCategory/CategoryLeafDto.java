package com.designpatternproject.dto.itemCategory;

import lombok.*;

@Value
@Getter
@Setter
@AllArgsConstructor
public class CategoryLeafDto implements CategoryComponentDto {
    private Long id;
    private String category;
    private Integer level;
    private Long parent;

}