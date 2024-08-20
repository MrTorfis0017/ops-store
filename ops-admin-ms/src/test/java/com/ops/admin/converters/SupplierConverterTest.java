package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.Supplier;
import com.ops.common.dto.SupplierDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getSupplier;
import static com.ops.admin.utils.GetTestData.getSupplierDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplierConverter.class})
public class SupplierConverterTest {

    @InjectMocks
    private SupplierConverter supplierConverter;

    @Test
    public void toDTOTest() {
        Supplier supplier = getSupplier();

        SupplierDTO result = supplierConverter.toDTO(supplier);

        assertEquals(supplier.getId(), result.getId());
        assertEquals(supplier.getName(), result.getName());
        assertEquals(supplier.getPhoneNumber(), result.getPhoneNumber());
    }

    @Test
    public void fromDTOTest() {
        SupplierDTO supplierDTO = getSupplierDTO();

        Supplier result = supplierConverter.fromDTO(supplierDTO);

        assertEquals(supplierDTO.getId(), result.getId());
        assertEquals(supplierDTO.getName(), result.getName());
        assertEquals(supplierDTO.getPhoneNumber(), result.getPhoneNumber());
    }
}
