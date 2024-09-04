package com.ops.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CharacteristicValueDTO implements Serializable {

    private Long id;

    private String value;

    private Long characteristicId;
}