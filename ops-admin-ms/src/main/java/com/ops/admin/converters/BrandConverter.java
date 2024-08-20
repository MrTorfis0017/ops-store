package com.ops.admin.converters;

import com.ops.admin.entities.Brand;
import com.ops.common.dto.BrandDTO;
import org.springframework.stereotype.Component;

@Component
public class BrandConverter {

    public BrandDTO toDTO(Brand brand) {
        BrandDTO result = new BrandDTO();
        result.setId(brand.getId());
        result.setName(brand.getName());
        return result;
    }

    public Brand fromDTO(BrandDTO brandDTO) {
        Brand result = new Brand();
        result.setId(brandDTO.getId());
        result.setName(brandDTO.getName());
        return result;
    }
}
