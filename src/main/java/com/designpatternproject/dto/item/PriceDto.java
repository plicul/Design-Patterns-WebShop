package com.designpatternproject.dto.item;

import com.designpatternproject.entity.Price;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Price}
 */
@Value
public class PriceDto implements Serializable {
    Long id;
    String priceType;
    String activeFlag;
    Long amount;
}