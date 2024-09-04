package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemDTO {

    private Long id;

    private Integer quantity;

    private ProductDTO productDTO;

    private Long orderId;
}
