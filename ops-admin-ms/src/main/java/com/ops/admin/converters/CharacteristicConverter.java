package com.ops.admin.converters;

import com.ops.admin.entities.Characteristic;
import com.ops.common.dto.CharacteristicDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacteristicConverter {

    public CharacteristicDTO toDTO(Characteristic characteristic) {
        CharacteristicDTO result = new CharacteristicDTO();
        result.setId(characteristic.getId());
        result.setName(characteristic.getName());
        return result;
    }

    public Characteristic fromDTO(CharacteristicDTO characteristicDTO) {
        Characteristic result = new Characteristic();
        result.setId(characteristicDTO.getId());
        result.setName(characteristicDTO.getName());
        return result;
    }
}
