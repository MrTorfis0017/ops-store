package com.ops.user.converters;

import com.ops.common.dto.OrderDTO;
import com.ops.common.dto.OrderItemDTO;
import com.ops.user.entities.Order;
import com.ops.user.entities.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final OrderItemsConverter orderItemsConverter;

    public OrderDTO toDTO(Order order) {
        OrderDTO result = new OrderDTO();
        result.setId(order.getId());
        //TODO remove this hardcode
        result.setCreatedBy(1L);
        result.setDeliveryStatus(order.getDeliveryStatus());
        result.setPaymentStatus(order.getPaymentStatus());
        List<OrderItemDTO> orderItemDTOs = new ArrayList<>();
        for (OrderItem item : order.getOrderItems()) {
            orderItemDTOs.add(orderItemsConverter.toDTO(item));
        }
        result.setOrderItems(orderItemDTOs);
        return result;
    }

    public Order fromDTO(OrderDTO orderDTO) {
        Order result = new Order();
        result.setId(orderDTO.getId());
        //TODO remove this hardcode
        result.setCreatedBy(1L);
        result.setDeliveryStatus(orderDTO.getDeliveryStatus());
        result.setPaymentStatus(orderDTO.getPaymentStatus());
        List<OrderItem> orderItemDTOs = new ArrayList<>();
        for (OrderItemDTO item : orderDTO.getOrderItems()) {
            orderItemDTOs.add(orderItemsConverter.fromDTO(item));
        }
        result.setOrderItems(orderItemDTOs);
        return result;
    }
}
