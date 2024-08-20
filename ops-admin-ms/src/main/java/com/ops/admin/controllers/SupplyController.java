package com.ops.admin.controllers;

import com.ops.admin.services.SupplyService;
import com.ops.common.dto.SupplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor
@RequestMapping("/admin/api/supply")
public class SupplyController {

    private final SupplyService supplyService;

    @Operation(description = "Creating supply")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PostMapping("/create")
    public SupplyDTO createProduct(@RequestBody SupplyDTO supplyDTO) {
        return supplyService.create(supplyDTO);
    }
}
