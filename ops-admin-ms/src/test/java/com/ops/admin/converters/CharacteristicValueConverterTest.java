package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Characteristic;
import com.ops.admin.entities.CharacteristicValue;
import com.ops.admin.repositories.CharacteristicRepository;
import com.ops.common.dto.CharacteristicValueDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getCharacteristicValue;
import static com.ops.admin.utils.GetTestData.getCharacteristicValueDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CharacteristicValueConverter.class})
public class CharacteristicValueConverterTest {

    @InjectMocks
    private CharacteristicValueConverter characteristicValueConverter;

    @Mock
    private CharacteristicRepository characteristicRepository;

    @Test
    public void toDTOTest() {
        CharacteristicValue characteristicValue = getCharacteristicValue();

        CharacteristicValueDTO result = characteristicValueConverter.toDTO(characteristicValue);

        assertEquals(characteristicValue.getId(), result.getId());
        assertEquals(characteristicValue.getValue(), result.getValue());
        assertEquals(characteristicValue.getCharacteristic().getId(), result.getCharacteristicId());
    }

    @Test
    public void fromDTOTest() {
        CharacteristicValueDTO characteristicValueDTO = getCharacteristicValueDTO();
        Characteristic characteristic = new Characteristic();
        characteristic.setId(characteristicValueDTO.getId());

        when(characteristicRepository.getReferenceById(characteristicValueDTO.getCharacteristicId())).thenReturn(characteristic);
        CharacteristicValue result = characteristicValueConverter.fromDTO(characteristicValueDTO);

        assertEquals(characteristicValueDTO.getId(), result.getId());
        assertEquals(characteristicValueDTO.getValue(), result.getValue());
        assertEquals(characteristicValueDTO.getCharacteristicId(), result.getCharacteristic().getId());
    }
}
