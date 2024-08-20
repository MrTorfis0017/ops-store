package com.ops.admin.converters;

import com.ops.admin.entities.CharacteristicValue;
import com.ops.admin.repositories.CharacteristicRepository;
import com.ops.common.dto.CharacteristicValueDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CharacteristicValueConverter {

    private final CharacteristicRepository characteristicRepository;

    public CharacteristicValueDTO toDTO(CharacteristicValue characteristicValue) {
        CharacteristicValueDTO result = new CharacteristicValueDTO();
        result.setId(characteristicValue.getId());
        result.setValue(characteristicValue.getValue());
        result.setCharacteristicId(characteristicValue.getCharacteristic().getId());
        return result;
    }

    public CharacteristicValue fromDTO(CharacteristicValueDTO characteristicValueDTO) {
        CharacteristicValue result = new CharacteristicValue();
        result.setId(characteristicValueDTO.getId());
        result.setValue(characteristicValueDTO.getValue());
        result.setCharacteristic(characteristicRepository.getReferenceById(characteristicValueDTO.getCharacteristicId()));
        return result;
    }
}
