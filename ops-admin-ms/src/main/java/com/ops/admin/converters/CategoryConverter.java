package com.ops.admin.converters;

import com.ops.admin.entities.Category;
import com.ops.admin.entities.Characteristic;
import com.ops.common.dto.CategoryDTO;
import com.ops.common.dto.CharacteristicDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final CharacteristicConverter characteristicConverter;

    public CategoryDTO toDTO(Category category) {
        CategoryDTO result = new CategoryDTO();
        result.setId(category.getId());
        result.setName(category.getName());
        if (category.getCategory() != null) {
            result.setCategory(toDTO(category.getCategory()));
        }

        List<CharacteristicDTO> characteristicList = new ArrayList<>();
        for (Characteristic item : category.getCharacteristicList()) {
            characteristicList.add(characteristicConverter.toDTO(item));
        }
        result.setCharacteristicList(characteristicList);
        return result;
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        Category result = new Category();
        result.setId(categoryDTO.getId());
        result.setName(categoryDTO.getName());
        if (categoryDTO.getCategory() != null) {
            result.setCategory(fromDTO(categoryDTO.getCategory()));
        }

        List<Characteristic> characteristicList = new ArrayList<>();
        for (CharacteristicDTO item : categoryDTO.getCharacteristicList()) {
            characteristicList.add(characteristicConverter.fromDTO(item));
        }
        result.setCharacteristicList(characteristicList);
        return result;
    }
}
