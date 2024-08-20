package com.ops.admin.converters;

import com.ops.admin.entities.CharacteristicValue;
import com.ops.admin.entities.Product;
import com.ops.admin.repositories.BrandRepository;
import com.ops.admin.repositories.CategoryRepository;
import com.ops.common.dto.CharacteristicValueDTO;
import com.ops.common.dto.ProductDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final CharacteristicValueConverter characteristicValueConverter;

    private final BrandRepository brandRepository;

    private final CategoryRepository categoryRepository;

    public ProductDTO toDTO(Product product) {
        ProductDTO result = new ProductDTO();
        result.setId(product.getId());
        result.setName(product.getName());
        result.setQuantity(product.getQuantity());
        result.setBrandId(product.getBrand().getId());
        result.setCategoryId(product.getCategory().getId());

        List<CharacteristicValueDTO> characteristicValueList = new ArrayList<>();
        for (CharacteristicValue item : product.getCharacteristicValueList()) {
            characteristicValueList.add(characteristicValueConverter.toDTO(item));
        }
        result.setCharacteristicValuesList(characteristicValueList);
        return result;
    }

    public Product fromDTO(ProductDTO productDTO) {
        Product result = new Product();
        result.setId(productDTO.getId());
        result.setName(productDTO.getName());
        result.setQuantity(productDTO.getQuantity());
        result.setBrand(brandRepository.getReferenceById(productDTO.getBrandId()));
        result.setCategory(categoryRepository.getReferenceById(productDTO.getCategoryId()));

        List<CharacteristicValue> characteristicValueList = new ArrayList<>();
        for (CharacteristicValueDTO item : productDTO.getCharacteristicValuesList()) {
            characteristicValueList.add(characteristicValueConverter.fromDTO(item));
        }
        result.setCharacteristicValueList(characteristicValueList);
        return result;
    }
}
