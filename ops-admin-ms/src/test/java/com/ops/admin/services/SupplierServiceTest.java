package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.SupplierConverter;
import com.ops.admin.entities.Supplier;
import com.ops.admin.repositories.SupplierRepository;
import com.ops.common.dto.SupplierDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getSupplier;
import static com.ops.admin.utils.GetTestData.getSupplierDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplierService.class})
public class SupplierServiceTest {

    @InjectMocks
    private SupplierService supplierService;

    @Mock
    private SupplierConverter supplierConverter;

    @Mock
    private SupplierRepository supplierRepository;

    @Test
    public void createSupplierTest() {
        Supplier supplier = getSupplier();
        SupplierDTO supplierDTO = getSupplierDTO();

        when(supplierConverter.fromDTO(any())).thenReturn(supplier);
        when(supplierRepository.save(any())).thenReturn(supplier);
        when(supplierConverter.toDTO(any())).thenReturn(supplierDTO);

        SupplierDTO result = supplierService.create(supplierDTO);

        assertEquals(supplierDTO.getId(), result.getId());
        assertEquals(supplierDTO.getName(), result.getName());
        assertEquals(supplierDTO.getPhoneNumber(), result.getPhoneNumber());
    }
}
