package com.ops.admin.converters;

import com.ops.admin.entities.Supply;
import com.ops.admin.entities.SupplyItem;
import com.ops.admin.repositories.SupplierRepository;
import com.ops.common.dto.SupplyDTO;
import com.ops.common.dto.SupplyItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SupplyConverter {

    private final SupplyItemConverter supplyItemConverter;

    private final SupplierRepository supplierRepository;

    public SupplyDTO toDTO(Supply supply) {
        SupplyDTO result = new SupplyDTO();
        result.setId(supply.getId());
        result.setCreatedDate(supply.getCreatedDate());
        result.setDeliveredDate(supply.getDeliveredDate());
        result.setCreatedBy(supply.getCreatedBy());
        result.setReceivedBy(supply.getReceivedBy());
        result.setSupplierId(supply.getSupplier().getId());
        List<SupplyItemDTO> supplyItemDTOList = new ArrayList<>();
        for (SupplyItem item : supply.getSupplyItems()) {
            supplyItemDTOList.add(supplyItemConverter.toDTO(item));
        }
        result.setSupplyItems(supplyItemDTOList);
        return result;
    }

    public Supply fromDTO(SupplyDTO supplyDTO) {
        Supply result = new Supply();
        result.setId(supplyDTO.getId());
        result.setCreatedDate(supplyDTO.getCreatedDate());
        result.setDeliveredDate(supplyDTO.getDeliveredDate());
        result.setCreatedBy(supplyDTO.getCreatedBy());
        result.setReceivedBy(supplyDTO.getReceivedBy());
        result.setSupplier(supplierRepository.getReferenceById(supplyDTO.getSupplierId()));
        List<SupplyItem> supplyItemList = new ArrayList<>();
        for (SupplyItemDTO item : supplyDTO.getSupplyItems()) {
            supplyItemList.add(supplyItemConverter.fromDTO(item));
        }
        result.setSupplyItems(supplyItemList);
        return result;
    }
}
