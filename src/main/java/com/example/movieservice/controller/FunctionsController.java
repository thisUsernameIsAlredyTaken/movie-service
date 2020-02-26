package com.example.movieservice.controller;

import com.example.movieservice.service.FunctionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class FunctionsController {

    @Autowired
    private FunctionsService functionsService;

    @GetMapping("search")
    public List<Map<String, Object>> search(@RequestParam String pattern,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "10") int pageSize) {
        return functionsService.search(pattern, page, pageSize);
    }

    @GetMapping("search/count")
    public Map<String, Integer> searchCount(@RequestParam String pattern) {
        Map<String, Integer> map = new HashMap<>();
        map.put("count", functionsService.searchCount(pattern));
        return map;
    }

    @GetMapping("recommend")
    public List<Map<String, Object>> recommend(@RequestParam String watched,
                                               @RequestParam String scores,
                                               @RequestParam(defaultValue = "0") int page,
                                               @RequestParam(defaultValue = "10") int pageSize) {
        return functionsService.recommend(watched, scores, page, pageSize);
    }
}
