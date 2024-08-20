package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.SupplyItem;
import com.ops.admin.repositories.ProductRepository;
import com.ops.common.dto.SupplyItemDTO;
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
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplyItemConverter.class})
public class SupplyItemConverterTest {

    @InjectMocks
    private SupplyItemConverter supplyItemConverter;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void toDTOTest() {
        SupplyItem supplyItem = getSupplyItem();

        SupplyItemDTO supplyItemDTO = supplyItemConverter.toDTO(supplyItem);

        assertEquals(supplyItem.getId(), supplyItemDTO.getId());
        assertEquals(supplyItem.getDeliveryPrice(), supplyItemDTO.getDeliveryPrice());
        assertEquals(supplyItem.getQuantity(), supplyItemDTO.getQuantity());
        assertEquals(supplyItem.getProduct().getId(), supplyItemDTO.getProductId());
    }

    @Test
    public void fromDTOTest() {
        SupplyItemDTO supplyItemDTO = getSupplyItemDTO();

        when(productRepository.getReferenceById(any())).thenReturn(getProduct());

        SupplyItem supplyItem = supplyItemConverter.fromDTO(supplyItemDTO);

        assertEquals(supplyItemDTO.getId(), supplyItem.getId());
        assertEquals(supplyItemDTO.getDeliveryPrice(), supplyItem.getDeliveryPrice());
        assertEquals(supplyItemDTO.getQuantity(), supplyItem.getQuantity());
        assertEquals(supplyItemDTO.getProductId(), supplyItem.getProduct().getId());
    }
}
