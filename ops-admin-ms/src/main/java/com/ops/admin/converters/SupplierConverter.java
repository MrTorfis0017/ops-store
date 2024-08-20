package com.ops.admin.converters;

import com.ops.admin.entities.Supplier;
import com.ops.common.dto.SupplierDTO;
import org.springframework.stereotype.Component;

@Component
public class SupplierConverter {

    public SupplierDTO toDTO(Supplier supplier) {
        SupplierDTO result = new SupplierDTO();
        result.setId(supplier.getId());
        result.setName(supplier.getName());
        result.setPhoneNumber(supplier.getPhoneNumber());
        return result;
    }

    public Supplier fromDTO(SupplierDTO supplierDTO) {
        Supplier result = new Supplier();
        result.setId(supplierDTO.getId());
        result.setName(supplierDTO.getName());
        result.setPhoneNumber(supplierDTO.getPhoneNumber());
        return result;
    }
}
