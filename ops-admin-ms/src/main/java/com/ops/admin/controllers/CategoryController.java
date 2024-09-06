package com.ops.admin.controllers;

import com.ops.admin.services.CategoryService;
import com.ops.common.dto.CategoryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(description = "Creating category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success|OK"),
            @ApiResponse(responseCode = "409", description = "Conflict|Already exist")
    })
    @PostMapping("/create")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }
}