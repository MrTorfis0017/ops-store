package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Characteristic;
import com.ops.common.dto.CharacteristicDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getCharacteristic;
import static com.ops.admin.utils.GetTestData.getCharacteristicDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CharacteristicConverter.class})
public class CharacteristicConverterTest {

    @InjectMocks
    private CharacteristicConverter characteristicConverter;

    @Test
    public void toDTOTest() {
        Characteristic characteristic = getCharacteristic();

        CharacteristicDTO result = characteristicConverter.toDTO(characteristic);

        assertEquals(characteristic.getId(), result.getId());
        assertEquals(characteristic.getName(), result.getName());
    }

    @Test
    public void fromDTOTest() {
        CharacteristicDTO characteristicDTO = getCharacteristicDTO();

        Characteristic result = characteristicConverter.fromDTO(characteristicDTO);

        assertEquals(characteristicDTO.getId(), result.getId());
        assertEquals(characteristicDTO.getName(), result.getName());
    }
}
