package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class ProductDTO implements Serializable {

    private Long id;

    private String name;

    private Integer quantity;

    private Long categoryId;

    private Long BrandId;

    private List<CharacteristicValueDTO> characteristicValuesList;
}