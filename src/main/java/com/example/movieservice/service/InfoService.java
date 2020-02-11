package com.example.movieservice.service;

import com.example.movieservice.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InfoService {

    @Autowired
    private MovieRepo movieRepo;

    private Map<String, Object> splitGenresToArray(Map<String, Object> movieMap) {
        Map<String, Object> newMovieMap = new HashMap<>();
        if (movieMap != null && movieMap.get("genres") != null) {
            newMovieMap.putAll(movieMap);
            String genres = (String) newMovieMap.get("genres");
            newMovieMap.replace("genres", genres.split(","));
        }
        return newMovieMap;
    }

    public Map<String, Object> findFullInfoById(String id) {
        return splitGenresToArray(movieRepo.getFullInfo(id).orElse(null));
    }

    public Map<String, Map<String, Object>> findFullInfoByIds(List<String> ids) {
        Map<String, Map<String, Object>> movies = new HashMap<>();
        movieRepo.getFullInfoAll(ids).forEach(stringObjectMap -> {
            Map<String, Object> uniteInfo = splitGenresToArray(stringObjectMap);
            uniteInfo.remove("id");
            movies.put((String) stringObjectMap.get("id"), uniteInfo);
        });
        return movies;
    }
}
