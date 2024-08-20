package com.ops.admin.controllers;

import com.ops.admin.services.SupplierService;
import com.ops.common.dto.SupplierDTO;
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
@RequestMapping("/admin/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    @Operation(description = "Creating supplier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK")
    })
    @PostMapping("/create")
    public SupplierDTO createProduct(@RequestBody SupplierDTO supplierDTO) {
        return supplierService.create(supplierDTO);
    }
}
