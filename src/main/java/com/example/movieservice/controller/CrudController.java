package com.example.movieservice.controller;

import com.example.movieservice.model.Movie;
import com.example.movieservice.repository.MovieRepo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CrudController {

    private final MovieRepo movieRepo;

    @GetMapping("u/{movieId}")
    public Movie findMovieById(@PathVariable String movieId) {
        return movieRepo.findById(movieId).orElse(null);
    }

    @PostMapping
    public void createNewMovie(@RequestBody Movie newMovie) {
        movieRepo.save(newMovie);
    }

    @GetMapping("all")
    public List<Movie> findAllMovies() {
        return movieRepo.findAll();
    }
}
