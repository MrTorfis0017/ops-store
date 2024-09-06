package com.ops.user.converters;

import com.ops.common.dto.OrderItemDTO;
import com.ops.user.entities.OrderItem;
import com.ops.user.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderItemsConverter {

    private final OrderRepository orderRepository;

    public OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO result = new OrderItemDTO();
        result.setId(orderItem.getId());
        result.setOrderId(orderItem.getOrder().getId());
        result.setProductId(orderItem.getProductId());
        result.setQuantity(orderItem.getQuantity());
        return result;
    }

    public OrderItem fromDTO(OrderItemDTO orderItemDTO) {
        OrderItem result = new OrderItem();
        result.setId(orderItemDTO.getId());
        if (orderItemDTO.getOrderId() != null)
            result.setOrder(orderRepository.getReferenceById(orderItemDTO.getOrderId()));
        else result.setOrder(null);
        result.setProductId(orderItemDTO.getProductId());
        result.setQuantity(orderItemDTO.getQuantity());
        return result;
    }

}
