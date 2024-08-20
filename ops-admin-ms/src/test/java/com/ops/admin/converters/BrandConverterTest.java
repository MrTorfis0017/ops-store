package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Brand;
import com.ops.common.dto.BrandDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getBrand;
import static com.ops.admin.utils.GetTestData.getBrandDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, BrandConverter.class})
public class BrandConverterTest {

    @InjectMocks
    private BrandConverter brandConverter;

    @Test
    public void toDTOTest() {
        Brand brand = getBrand();

        BrandDTO result = brandConverter.toDTO(brand);

        assertEquals(brand.getId(), result.getId());
        assertEquals(brand.getName(), result.getName());
    }

    @Test
    public void fromDTOTest() {
        BrandDTO brandDTO = getBrandDTO();

        Brand result = brandConverter.fromDTO(brandDTO);

        assertEquals(brandDTO.getId(), result.getId());
        assertEquals(brandDTO.getName(), result.getName());
    }
}
