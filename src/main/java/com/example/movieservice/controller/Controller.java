package com.example.movieservice.controller;

import com.example.movieservice.model.Movie;
import com.example.movieservice.model.MovieFullInfo;
import com.example.movieservice.repository.MovieRepo;
import com.example.movieservice.service.FunctionalService;
import com.example.movieservice.service.InfoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private FunctionalService functionalService;

    @Autowired
    private InfoService infoService;

    @GetMapping("search")
    public List<Movie> search(@RequestParam String pattern,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "10") int pageSize) {
        return functionalService.search(pattern, page, pageSize);
    }

    @GetMapping("recommend")
    public List<Movie> recommend(@RequestParam String watched,
                                 @RequestParam String scores,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int pageSize) {
        return functionalService.recommend(watched, scores, page, pageSize);
    }

    @GetMapping("/info/{movieId}")
    public ResponseEntity<MovieFullInfo> findById(@PathVariable String movieId) {
        MovieFullInfo m = infoService.findFullInfoById(movieId);
        if (m == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MovieFullInfo>(m, HttpStatus.OK);
    }
}
