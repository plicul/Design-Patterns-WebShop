package com.designpatternproject.dto.item;

import com.designpatternproject.entity.Item;
import com.designpatternproject.entity.Price;
import lombok.Value;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * DTO for {@link Item}
 */
@Value
public class ItemDto implements Serializable {
    Long id;
    String itemTitle;
    String itemDescription;
    String category;
    String imagePath;
    Integer quantity;
    List<PriceDto> prices;
}