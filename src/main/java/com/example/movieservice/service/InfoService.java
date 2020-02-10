package com.example.movieservice.service;

import com.example.movieservice.model.Movie;
import com.example.movieservice.model.MovieFullInfo;
import com.example.movieservice.model.Rating;
import com.example.movieservice.repository.MovieRepo;
import com.example.movieservice.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfoService {

    @Autowired
    private MovieRepo movieRepo;

    @Autowired
    private RatingRepo ratingRepo;

    public Movie findMovieById(String id) {
        return movieRepo.findById(id).orElse(null);
    }

    public Rating findRatingById(String id) {
        return ratingRepo.findById(id).orElse(null);
    }

    public MovieFullInfo findFullInfoById(String id) {
        return movieRepo.getFullInfo(id).orElse(null);
    }
}
