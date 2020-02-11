package com.example.movieservice.service;

import com.example.movieservice.model.Movie;
import com.example.movieservice.repository.MovieRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FunctionsService {

    private final MovieRepo movieRepo;

    public List<Movie> search(String pattern, int page, int pageSize) {
        return movieRepo.search(pattern, page, pageSize);
    }

    public List<Movie> recommend(String watched, String scores, int page, int pageSize) {
        return movieRepo.recommend(watched, scores, page, pageSize);
    }
}
