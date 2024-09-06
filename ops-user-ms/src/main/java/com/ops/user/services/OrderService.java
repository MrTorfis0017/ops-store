package com.ops.user.services;

import com.ops.common.dto.OrderDTO;
import com.ops.user.converters.OrderConverter;
import com.ops.user.entities.Order;
import com.ops.user.repositories.OrderItemRepository;
import com.ops.user.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderConverter orderConverter;

    private final OrderItemRepository orderItemRepository;

    public OrderDTO create(OrderDTO orderDTO) {
        Order order = orderRepository.save(orderConverter.fromDTO(orderDTO));
        order.getOrderItems().forEach(item->item.setOrder(order));
        orderItemRepository.saveAll(order.getOrderItems());
        return orderConverter.toDTO(order);
    }
}
