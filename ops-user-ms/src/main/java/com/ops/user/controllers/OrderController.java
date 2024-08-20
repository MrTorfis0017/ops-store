package com.ops.user.controllers;

import com.ops.common.dto.OrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/order")
public class OrderController {

    @PostMapping("/create")
    public OrderDTO create(@RequestBody OrderDTO order) {
        return new OrderDTO();
    }
}
