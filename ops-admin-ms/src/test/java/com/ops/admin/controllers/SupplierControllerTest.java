package com.ops.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.services.SupplierService;
import com.ops.common.dto.SupplierDTO;
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

import static com.ops.admin.utils.GetTestData.getSupplierDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplierController.class})
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Test
    public void createSupplierTest() throws Exception {
        SupplierDTO supplierDTO = getSupplierDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(supplierDTO);
        when(supplierService.create(any())).thenReturn(supplierDTO);

        ResultActions resultActions = mockMvc.perform(post("/admin/api/supplier/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
    }
}
