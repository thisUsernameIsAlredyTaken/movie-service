package com.example.movieservice.controller;

import com.example.movieservice.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @GetMapping("info/{movieId}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable String movieId) {
        Map<String, Object> m = infoService.findFullInfoById(movieId);
        if (m == null || m.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(m);
    }

    @GetMapping("info")
    public Map<String, Map<String, Object>> findAllById(@RequestParam String movieIds) {
        List<String> ids = Arrays.asList(movieIds.split(","));
        return infoService.findFullInfoByIds(ids);
    }
}
