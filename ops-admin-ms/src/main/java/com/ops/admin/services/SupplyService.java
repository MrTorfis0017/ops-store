package com.ops.admin.services;

import com.ops.admin.converters.SupplyConverter;
import com.ops.admin.entities.Supply;
import com.ops.admin.entities.SupplyItem;
import com.ops.admin.repositories.SupplyItemRepository;
import com.ops.admin.repositories.SupplyRepository;
import com.ops.common.dto.SupplyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplyService {

    private final SupplyConverter supplyConverter;

    private final SupplyRepository supplyRepository;

    private final SupplyItemRepository supplyItemRepository;

    public SupplyDTO create(SupplyDTO supplyDTO) {
        Supply supply = supplyConverter.fromDTO(supplyDTO);
        SupplyDTO result = supplyConverter.toDTO(supplyRepository.save(supply));
        for (SupplyItem item : supply.getSupplyItems()) {
            item.setSupply(supplyRepository.getReferenceById(result.getId()));
        }
        supplyItemRepository.saveAll(supply.getSupplyItems());
        return result;
    }
}
