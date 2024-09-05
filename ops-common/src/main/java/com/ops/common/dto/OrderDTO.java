package com.ops.common.dto;

import com.ops.common.enums.DeliveryStatus;
import com.ops.common.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderDTO {

    private Long id;

    private Long createdBy;

    private PaymentStatus paymentStatus;

    private DeliveryStatus deliveryStatus;

    private List<OrderItemDTO> orderItems;
}