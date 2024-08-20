package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Category;
import com.ops.common.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getCategory;
import static com.ops.admin.utils.GetTestData.getCategoryDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CategoryConverterTest.class})
public class CategoryConverterTest {

    @InjectMocks
    private CategoryConverter categoryConverter;

    @Mock
    private CharacteristicConverter characteristicConverter;

    @Test
    public void toDTOTest() {
        Category category = getCategory();

        when(characteristicConverter.toDTO(any())).thenReturn(getCategoryDTO().getCharacteristicList().get(0));
        CategoryDTO result = categoryConverter.toDTO(category);

        assertEquals(category.getId(), result.getId());
        assertEquals(category.getName(), result.getName());
        assertEquals(category.getCategory().getName(), result.getCategory().getName());
        assertEquals(category.getCharacteristicList().get(0).getName(), result.getCharacteristicList().get(0).getName());
    }

    @Test
    public void fromDTOTest() {
        CategoryDTO categoryDTO = getCategoryDTO();

        when(characteristicConverter.fromDTO(any())).thenReturn(getCategory().getCharacteristicList().get(0));
        Category result = categoryConverter.fromDTO(categoryDTO);

        assertEquals(categoryDTO.getId(), result.getId());
        assertEquals(categoryDTO.getName(), result.getName());
        assertEquals(categoryDTO.getCategory().getName(), result.getCategory().getName());
        assertEquals(categoryDTO.getCharacteristicList().get(0).getName(), result.getCharacteristicList().get(0).getName());
    }
}
