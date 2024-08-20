package com.ops.admin.services;

import com.ops.admin.converters.SupplierConverter;
import com.ops.admin.entities.Supplier;
import com.ops.admin.repositories.SupplierRepository;
import com.ops.common.dto.SupplierDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierService {

    private final SupplierConverter supplierConverter;

    private final SupplierRepository supplierRepository;

    public SupplierDTO create(SupplierDTO supplierDTO) {
        Supplier supplier = supplierRepository.save(supplierConverter.fromDTO(supplierDTO));
        return supplierConverter.toDTO(supplier);
    }
}
