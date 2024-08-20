package com.ops.admin.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.services.BrandService;
import com.ops.admin.services.CategoryService;
import com.ops.common.dto.BrandDTO;
import com.ops.common.dto.CategoryDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CategoryController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, CategoryController.class})
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    @Test
    public void createCategoryTest() throws Exception {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(1L);
        categoryDTO.setName("Test category");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(categoryDTO);

        when(categoryService.create(any())).thenReturn(categoryDTO);

        ResultActions resultActions = mockMvc.perform(post("/admin/api/category/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
    }
}
