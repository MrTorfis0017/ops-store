package com.ops.admin.converters;

import com.ops.admin.entities.News;
import com.ops.common.dto.NewsDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class NewsConverter {

    public NewsDTO toDTO(News news) {
        NewsDTO newsDto = new NewsDTO();
        newsDto.setId(news.getId());
        newsDto.setTitle(news.getTitle());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        newsDto.setCreatedBy(authentication.getName());
        //TODO will be implement in another task

        //newsDto.setImg(news.getImg());
        newsDto.setStartDate(news.getStartDate());
        newsDto.setEndDate(news.getEndDate());
        return newsDto;
    }

    public News fromDTO(NewsDTO newsDto) {
        News news = new News();
        news.setId(newsDto.getId());
        news.setTitle(newsDto.getTitle());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        news.setCreatedBy(authentication.getName());
        //TODO will be implement in another task

        //news.setImg(newsDto.getImg());
        news.setStartDate(newsDto.getStartDate());
        news.setEndDate(newsDto.getEndDate());
        return news;
    }
}
