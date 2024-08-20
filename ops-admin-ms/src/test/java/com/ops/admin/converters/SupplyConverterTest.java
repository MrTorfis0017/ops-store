package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Supply;
import com.ops.admin.repositories.SupplierRepository;
import com.ops.common.dto.SupplyDTO;
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
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplyConverter.class})
public class SupplyConverterTest {

    @InjectMocks
    private SupplyConverter supplyConverter;

    @Mock
    private SupplyItemConverter supplyItemConverter;

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    public void toDTOTest() {
        Supply supply = getSupply();

        SupplyDTO result = supplyConverter.toDTO(supply);

        assertEquals(supply.getId(), result.getId());
        assertEquals(supply.getCreatedBy(), result.getCreatedBy());
        assertEquals(supply.getDeliveredDate(), result.getDeliveredDate());
        assertEquals(supply.getCreatedBy(), result.getCreatedBy());
        assertEquals(supply.getReceivedBy(), result.getReceivedBy());
        assertEquals(supply.getSupplier().getId(), result.getSupplierId());
        assertEquals(supply.getSupplyItems().size(), result.getSupplyItems().size());
    }

    @Test
    public void fromDTOTest() {
        SupplyDTO supplyDTO = getSupplyDTO();

        when(supplierRepository.getReferenceById(any())).thenReturn(getSupplier());

        Supply result = supplyConverter.fromDTO(supplyDTO);

        assertEquals(supplyDTO.getId(), result.getId());
        assertEquals(supplyDTO.getCreatedBy(), result.getCreatedBy());
        assertEquals(supplyDTO.getDeliveredDate(), result.getDeliveredDate());
        assertEquals(supplyDTO.getCreatedBy(), result.getCreatedBy());
        assertEquals(supplyDTO.getReceivedBy(), result.getReceivedBy());
        assertEquals(supplyDTO.getSupplierId(), result.getSupplier().getId());
        assertEquals(supplyDTO.getSupplyItems().size(), result.getSupplyItems().size());
    }
}
