package com.example.movieservice.service;

import com.example.movieservice.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FunctionsService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private DataFormatService dataFormatService;

    public List<Map<String, Object>> search(String pattern, int page, int pageSize) {
        return dataFormatService.formatMovies(movieRepo.search(pattern, page, pageSize));
    }

    public List<Map<String, Object>> recommend(String watched, String scores,
                                               int page, int pageSize) {
        return dataFormatService.formatMovies(movieRepo.recommend(watched, scores, page, pageSize));
    }
}
