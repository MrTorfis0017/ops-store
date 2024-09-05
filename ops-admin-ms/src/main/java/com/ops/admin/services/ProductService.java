package com.ops.admin.services;

import com.ops.admin.converters.ProductConverter;
import com.ops.admin.entities.CharacteristicValue;
import com.ops.admin.entities.Product;
import com.ops.admin.repositories.CharacteristicValueRepository;
import com.ops.admin.repositories.ProductRepository;
import com.ops.common.dto.ProductDTO;
import com.ops.common.exceptions.OpsErrorCode;
import com.ops.common.exceptions.OpsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    private final CharacteristicValueRepository characteristicValueRepository;

    private final ProductConverter productConverter;

    public ProductDTO create(ProductDTO productDTO) {
        if (productRepository.getByName(productDTO.getName()) != null) {
            throw OpsException.errorWithDescription(OpsErrorCode.PRODUCT_ALREADY_EXIST, productDTO.getName());
        }
        Product productToSave = productRepository.save(productConverter.fromDTO(productDTO));
        for (CharacteristicValue item : productToSave.getCharacteristicValueList()) {
            item.setProduct(productToSave);
        }
        characteristicValueRepository.saveAll(productToSave.getCharacteristicValueList());
        return productConverter.toDTO(productToSave);
    }

    @Cacheable(value = "users", key = "#id")
    public ProductDTO get(Long id) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return productConverter.toDTO(productRepository.getReferenceById(id));
    }
}
