package com.ops.admin.services;

import com.ops.admin.converters.BrandConverter;
import com.ops.admin.entities.Brand;
import com.ops.admin.repositories.BrandRepository;
import com.ops.common.dto.BrandDTO;
import com.ops.common.exceptions.OpsErrorCode;
import com.ops.common.exceptions.OpsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    private final BrandConverter brandConverter;

    public BrandDTO create(BrandDTO brandDTO) {
        if (brandRepository.getByName(brandDTO.getName())!=null) {
            throw OpsException.errorWithDescription(OpsErrorCode.BRAND_ALREADY_EXIST, brandDTO.getName());
        }
        Brand savedBrand = brandRepository.save(brandConverter.fromDTO(brandDTO));
        return brandConverter.toDTO(savedBrand);
    }
}