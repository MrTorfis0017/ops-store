package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.CategoryConverter;
import com.ops.admin.entities.Category;
import com.ops.admin.entities.Characteristic;
import com.ops.admin.repositories.CategoryRepository;
import com.ops.admin.repositories.CharacteristicRepository;
import com.ops.common.dto.CategoryDTO;
import com.ops.common.dto.CharacteristicDTO;
import com.ops.common.exceptions.OpsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CategoryService.class})
public class CategoryServiceTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryConverter categoryConverter;

    @Mock
    private CharacteristicRepository characteristicRepository;

    @Test
    public void createCategoryTest() {
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDTO();

        when(categoryConverter.fromDTO(any())).thenReturn(category);
        when(categoryRepository.save(any())).thenReturn(category);
        when(categoryConverter.toDTO(any())).thenReturn(categoryDTO);

        CategoryDTO result = categoryService.create(categoryDTO);

        assertEquals(categoryDTO.getId(), result.getId());
        assertEquals(categoryDTO.getName(), result.getName());
        assertEquals(categoryDTO.getCharacteristicList(), result.getCharacteristicList());
    }

    @Test
    public void createCategoryWithExceptionTest() {
        Category category = getCategory();
        CategoryDTO categoryDTO = getCategoryDTO();

        when(categoryRepository.getByName(categoryDTO.getName())).thenReturn(category);

        assertThrows(OpsException.class, () -> categoryService.create(categoryDTO));
    }

    public Category getCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setName("Test category");
        List<Characteristic> characteristicList = new ArrayList<>();
        Characteristic characteristic = new Characteristic();
        characteristic.setId(1L);
        characteristic.setName("Some name");
        characteristicList.add(characteristic);
        category.setCharacteristicList(characteristicList);
        return category;
    }

    public CategoryDTO getCategoryDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test category");
        List<CharacteristicDTO> characteristicList = new ArrayList<>();
        CharacteristicDTO characteristicDTO = new CharacteristicDTO();
        characteristicDTO.setId(1L);
        characteristicDTO.setName("Some name");
        characteristicList.add(characteristicDTO);
        categoryDTO.setCharacteristicList(characteristicList);
        return categoryDTO;
    }
}
