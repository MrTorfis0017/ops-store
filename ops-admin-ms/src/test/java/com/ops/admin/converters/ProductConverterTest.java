package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Product;
import com.ops.admin.repositories.BrandRepository;
import com.ops.admin.repositories.CategoryRepository;
import com.ops.common.dto.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, ProductConverter.class})
public class ProductConverterTest {

    @InjectMocks
    private ProductConverter productConverter;

    @Mock
    private CharacteristicValueConverter characteristicValueConverter;

    @Mock
    private BrandRepository brandRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    public void toDTOTest() {
        Product product = getProduct();

        when(characteristicValueConverter.toDTO(any())).thenReturn(getCharacteristicValueDTO());

        ProductDTO result = productConverter.toDTO(product);

        assertEquals(product.getId(), result.getId());
        assertEquals(product.getName(), result.getName());
        assertEquals(product.getQuantity(), result.getQuantity());
        assertEquals(product.getBrand().getId(), result.getBrandId());
        assertEquals(product.getCategory().getId(), result.getCategoryId());
        assertEquals(product.getCharacteristicValueList().get(0).getId(), result.getCharacteristicValuesList().get(0).getId());
    }

    @Test
    public void fromDTOTest() {
        ProductDTO productDTO = getProductDTO();

        when(characteristicValueConverter.fromDTO(any())).thenReturn(getCharacteristicValue());
        when(brandRepository.getReferenceById(productDTO.getBrandId())).thenReturn(getBrand());
        when(categoryRepository.getReferenceById(productDTO.getCategoryId())).thenReturn(getCategory());

        Product result = productConverter.fromDTO(productDTO);

        assertEquals(productDTO.getId(), result.getId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getQuantity(), result.getQuantity());
        assertEquals(productDTO.getBrandId(), result.getBrand().getId());
        assertEquals(productDTO.getCategoryId(), result.getCategory().getId());
        assertEquals(productDTO.getCharacteristicValuesList().get(0).getId(), result.getCharacteristicValueList().get(0).getId());
    }
}
