package com.ops.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.services.SupplyService;
import com.ops.common.dto.SupplyDTO;
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

import static com.ops.admin.utils.GetTestData.getSupplyDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, SupplyController.class})
public class SupplyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplyService supplyService;

    @Test
    public void createSupplyTest() throws Exception {
        SupplyDTO supplyDTO = getSupplyDTO();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(supplyDTO);
        when(supplyService.create(any())).thenReturn(supplyDTO);

        ResultActions resultActions = mockMvc.perform(post("/admin/api/supply/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
        json = json.replace("1212121212121", "\"2008-05-30T04:20:12.121+00:00\"");
        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
    }
}
