package com.ops.admin.services;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.converters.NewsConverter;
import com.ops.admin.entities.News;
import com.ops.admin.repositories.NewsRepository;
import com.ops.common.dto.NewsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ContextConfiguration(classes = {TestSecurityConfig.class, NewsService.class})
public class NewsServiceTest {

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @Mock
    private NewsConverter newsConverter;

    @Test
    public void createNewsTest() {
        News news = getNews();
        NewsDTO newsDTO = getNewsDto();

        when(newsConverter.fromDTO(any())).thenReturn(news);
        when(newsRepository.save(any())).thenReturn(news);
        when(newsConverter.toDTO(any())).thenReturn(newsDTO);

        NewsDTO result = newsService.create(newsDTO);

        assertEquals(newsDTO.getId(), result.getId());
        assertEquals(newsDTO.getTitle(), result.getTitle());
//        assertEquals(newsDto.getImg(), result.getImg());
        assertEquals(newsDTO.getStartDate(), result.getStartDate());
        assertEquals(newsDTO.getEndDate(), result.getEndDate());
    }

    private News getNews() {
        News news = new News();
        news.setId(1L);
        news.setTitle("Some text");
//        news.setImg("Some img");
        news.setStartDate(new Date());
        news.setEndDate(new Date());

        return news;
    }

    private NewsDTO getNewsDto() {
        NewsDTO newsDTO = new NewsDTO();
        newsDTO.setId(1L);
        newsDTO.setTitle("Some text");
//        newsDto.setImg("Some img");
        newsDTO.setStartDate(new Date());
        newsDTO.setEndDate(new Date());

        return newsDTO;
    }
}
