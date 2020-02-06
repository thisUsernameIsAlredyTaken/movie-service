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
    @Column(length = 11)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String genres;

    private int startYear;

    private Integer endYear;

    public String[] getGenres() {
        return genres.split(",");
    }

    public void setGenres(String... genres) {
        String stringGenres = String.join(",", genres);
        for (char c : stringGenres.toCharArray()) {
            if (c != ',' && !Character.isLetter(c)) {
                throw new IllegalArgumentException();
            }
        }
        this.genres = stringGenres.toLowerCase();
    }
}
