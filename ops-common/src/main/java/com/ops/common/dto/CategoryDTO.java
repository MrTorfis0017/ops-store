package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO {

    private Long id;

    private String name;

    private CategoryDTO category;

    private List<CharacteristicDTO> characteristicList;
}
