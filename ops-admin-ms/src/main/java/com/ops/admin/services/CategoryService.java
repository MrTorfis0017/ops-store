package com.ops.admin.services;

import com.ops.admin.converters.CategoryConverter;
import com.ops.admin.entities.Category;
import com.ops.admin.entities.Characteristic;
import com.ops.admin.repositories.CategoryRepository;
import com.ops.admin.repositories.CharacteristicRepository;
import com.ops.common.dto.CategoryDTO;
import com.ops.common.exceptions.OpsErrorCode;
import com.ops.common.exceptions.OpsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryConverter categoryConverter;

    private final CharacteristicRepository characteristicRepository;

    public CategoryDTO create(CategoryDTO categoryDTO) {
        if (categoryRepository.getByName(categoryDTO.getName())!=null) {
            throw OpsException.errorWithDescription(OpsErrorCode.CATEGORY_ALREADY_EXIST, categoryDTO.getName());
        }
        Category savedCategory = categoryRepository.save(categoryConverter.fromDTO(categoryDTO));
        for (Characteristic item : savedCategory.getCharacteristicList()) {
            item.setCategory(savedCategory);
            characteristicRepository.save(item);
        }
        return categoryConverter.toDTO(savedCategory);
    }
}