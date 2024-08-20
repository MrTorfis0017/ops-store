package com.ops.admin.controllers;

import com.ops.admin.services.BrandService;
import com.ops.common.dto.BrandDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/api/brand")
public class BrandController {

    private final BrandService brandService;

    @Operation(description = "Creating category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "409", description = "Conflict|Already exist")
    })

    @PostMapping("/create")
    public BrandDTO createCategory(@RequestBody BrandDTO brandDTO) {
        return brandService.create(brandDTO);
    }
}