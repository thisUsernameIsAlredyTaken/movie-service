package com.example.movieservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Movie {

    @Id
    private String id;

    private String title;

    private String type;

    private String genres;

    private Integer runtime;

    private Integer startYear;

    private Integer endYear;

    private String description;

    public String[] getGenres() {
        return genres.split(",");
    }

    public void setGenres(String... genres) {
        String stringGenres = String.join(",", genres);
        this.genres = stringGenres.toLowerCase();
    }
}
