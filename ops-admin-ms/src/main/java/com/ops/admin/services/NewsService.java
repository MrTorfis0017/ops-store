package com.ops.admin.services;

import com.ops.admin.converters.NewsConverter;
import com.ops.admin.entities.News;
import com.ops.admin.repositories.NewsRepository;
import com.ops.common.dto.NewsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    private final NewsConverter newsConverter;

    public NewsDTO create(NewsDTO newsDto) {
        News newsToSave = newsConverter.fromDTO(newsDto);
        return newsConverter.toDTO(newsRepository.save(newsToSave));
    }
}
