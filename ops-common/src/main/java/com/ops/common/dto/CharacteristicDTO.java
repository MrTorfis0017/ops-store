package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CharacteristicDTO {

    private Long id;

    private String name;

    private List<CharacteristicValueDTO> characteristicListValue;
}