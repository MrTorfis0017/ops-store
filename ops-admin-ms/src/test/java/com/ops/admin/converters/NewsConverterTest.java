package com.ops.admin.converters;

import com.ops.admin.config.TestSecurityConfig;
import com.ops.admin.entities.News;
import com.ops.common.dto.NewsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;

import static com.ops.admin.utils.GetTestData.getNews;
import static com.ops.admin.utils.GetTestData.getNewsDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {TestSecurityConfig.class, NewsConverterTest.class})
@ExtendWith(MockitoExtension.class)
public class NewsConverterTest {

    @InjectMocks
    private NewsConverter newsConverter;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @Test
    public void toDTOTest() {
        News news = getNews();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        NewsDTO result = newsConverter.toDTO(news);

        assertEquals(news.getId(), result.getId());
        assertEquals(news.getTitle(), result.getTitle());
        // TODO when issue with images will fixed
//        assertEquals(news.getImg(), result.getImg());
        assertEquals(news.getStartDate(), result.getStartDate());
        assertEquals(news.getEndDate(), result.getEndDate());
    }

    @Test
    public void fromDTOTest() {
        NewsDTO newsDto = getNewsDto();

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        News result = newsConverter.fromDTO(newsDto);

        assertEquals(newsDto.getId(), result.getId());
        assertEquals(newsDto.getTitle(), result.getTitle());
        // TODO when issue with images will fixed
//        assertEquals(newsDto.getImg(), result.getImg());
        assertEquals(newsDto.getStartDate(), result.getStartDate());
        assertEquals(newsDto.getEndDate(), result.getEndDate());
    }
}
