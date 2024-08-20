package com.ops.admin.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.services.NewsService;
import com.ops.common.dto.NewsDTO;
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

@WebMvcTest(controllers = NewsController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, NewsController.class})
public class NewsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NewsService newsService;

    @Test
    public void createNewsTest() throws Exception {
        NewsDTO newsDto = new NewsDTO();
        newsDto.setId(1L);
        newsDto.setTitle("Some text");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newsDto);

        when(newsService.create(any())).thenReturn(newsDto);

        ResultActions resultActions = mockMvc.perform(post("/admin/api/news/create").contentType(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());

        assertEquals(json, resultActions.andReturn().getResponse().getContentAsString());
    }
}
