package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacteristicValueDTO {

    private Long id;

    private String value;

    private Long characteristicId;
}