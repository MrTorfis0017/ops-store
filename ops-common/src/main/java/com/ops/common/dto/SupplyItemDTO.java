package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SupplyItemDTO {

    private Long id;

    private BigDecimal deliveryPrice;

    private Integer quantity;

    private Long productId;
}