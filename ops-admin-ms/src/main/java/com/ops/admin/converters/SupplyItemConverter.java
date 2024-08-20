package com.ops.admin.converters;

import com.ops.admin.entities.SupplyItem;
import com.ops.admin.repositories.ProductRepository;
import com.ops.common.dto.SupplyItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SupplyItemConverter {

    private final ProductRepository productRepository;

    public SupplyItemDTO toDTO(SupplyItem supplyItem) {
        SupplyItemDTO result = new SupplyItemDTO();
        result.setId(supplyItem.getId());
        result.setDeliveryPrice(supplyItem.getDeliveryPrice());
        result.setQuantity(supplyItem.getQuantity());
        result.setProductId(supplyItem.getProduct().getId());
        return result;
    }

    public SupplyItem fromDTO(SupplyItemDTO supplyItemDTO) {
        SupplyItem result = new SupplyItem();
        result.setId(supplyItemDTO.getId());
        result.setDeliveryPrice(supplyItemDTO.getDeliveryPrice());
        result.setQuantity(supplyItemDTO.getQuantity());
        result.setProduct(productRepository.getReferenceById(supplyItemDTO.getProductId()));
        return result;
    }
}
