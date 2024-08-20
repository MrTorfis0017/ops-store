package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.BrandConverter;
import com.ops.admin.entities.Brand;
import com.ops.admin.repositories.BrandRepository;
import com.ops.common.dto.BrandDTO;
import com.ops.common.exceptions.OpsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, BrandService.class})
public class BrandServiceTest {

    @InjectMocks
    private BrandService brandService;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private BrandConverter brandConverter;

    @Test
    public void createBrandTest() {
        Brand brand = getBrand();
        BrandDTO brandDTO = getBrandDTO();

        when(brandConverter.fromDTO(any())).thenReturn(brand);
        when(brandRepository.save(any())).thenReturn(brand);
        when(brandConverter.toDTO(any())).thenReturn(brandDTO);

        BrandDTO result = brandService.create(brandDTO);

        assertEquals(brandDTO.getId(), result.getId());
        assertEquals(brandDTO.getName(), result.getName());
    }

    @Test
    public void createBrandWithExceptionTest() {
        Brand brand = getBrand();
        BrandDTO brandDTO = getBrandDTO();

        when(brandRepository.getByName(brandDTO.getName())).thenReturn(brand);

        assertThrows(OpsException.class, () -> brandService.create(brandDTO));
    }

    public Brand getBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setName("Test brand");
        return brand;
    }

    public BrandDTO getBrandDTO() {
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setId(1L);
        brandDTO.setName("Test brand");
        return brandDTO;
    }
}
