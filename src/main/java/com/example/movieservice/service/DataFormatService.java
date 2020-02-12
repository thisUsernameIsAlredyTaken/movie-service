package com.example.movieservice.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DataFormatService {

    public List<Map<String, Object>> formatMovies(List<Map<String, Object>> movies) {
        List<Map<String, Object>> newMovies = new ArrayList<>();
        for (Map<String, Object> movie : movies) {
            Map<String, Object> map = formatMovie(movie);
            if (!map.isEmpty()) newMovies.add(map);
        }
        return newMovies;
    }

    public Map<String, Object> formatMovie(Map<String, Object> movie) {
        if (movie == null || movie.isEmpty()) return new HashMap<>();
        Map<String, Object> newMovie = new HashMap<>(movie);
        String genres = (String) newMovie.get("genres");
        newMovie.replace("genres", genres.split(","));
        return newMovie;
    }
}
