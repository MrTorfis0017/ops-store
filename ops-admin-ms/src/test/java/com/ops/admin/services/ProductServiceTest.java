package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.ProductConverter;
import com.ops.admin.entities.CharacteristicValue;
import com.ops.admin.entities.Product;
import com.ops.admin.repositories.CharacteristicValueRepository;
import com.ops.admin.repositories.ProductRepository;
import com.ops.common.dto.CharacteristicValueDTO;
import com.ops.common.dto.ProductDTO;
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
@ContextConfiguration(classes = {TestSecurityConfig.class, ProductService.class})
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductConverter productConverter;

    @Mock
    private CharacteristicValueRepository characteristicValueRepository;

    @Test
    public void createProductTest() {
        Product product = getProduct();
        ProductDTO productDTO = getProductDTO();

        when(productConverter.fromDTO(any())).thenReturn(product);
        when(productRepository.save(any())).thenReturn(product);
        when(productConverter.toDTO(any())).thenReturn(productDTO);

        ProductDTO result = productService.create(productDTO);

        assertEquals(productDTO.getId(), result.getId());
        assertEquals(productDTO.getName(), result.getName());
        assertEquals(productDTO.getCharacteristicValuesList(), result.getCharacteristicValuesList());
    }

    @Test
    public void createProductWithExceptionTest() {
        Product product = getProduct();
        ProductDTO productDTO = getProductDTO();

        when(productRepository.getByName(productDTO.getName())).thenReturn(product);

        assertThrows(OpsException.class, () -> productService.create(productDTO));
    }

    public Product getProduct() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Test product");
        List<CharacteristicValue> characteristicValues = new ArrayList<>();
        CharacteristicValue characteristicValue = new CharacteristicValue();
        characteristicValue.setId(1L);
        characteristicValue.setValue("Some value");
        characteristicValues.add(characteristicValue);
        product.setCharacteristicValueList(characteristicValues);
        return product;
    }

    public ProductDTO getProductDTO() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test product");
        List<CharacteristicValueDTO> characteristicValues = new ArrayList<>();
        CharacteristicValueDTO characteristicValueDTO = new CharacteristicValueDTO();
        characteristicValueDTO.setId(1L);
        characteristicValueDTO.setValue("Some value");
        characteristicValues.add(characteristicValueDTO);
        productDTO.setCharacteristicValuesList(characteristicValues);
        return productDTO;
    }
}
