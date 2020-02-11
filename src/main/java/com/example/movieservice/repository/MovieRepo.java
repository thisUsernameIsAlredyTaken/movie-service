package com.example.movieservice.repository;

import com.example.movieservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface MovieRepo extends JpaRepository<Movie, String> {

    @Query(nativeQuery = true,
            value = "select m.* from search_movies(:pattern, :page, :page_size) " +
                    "join movie m using (id)")
    List<Movie> search(@Param("pattern") String pattern,
                       @Param("page") int page,
                       @Param("page_size") int pageSize);

    @Query(nativeQuery = true,
            value = "select m.* from recommended_movies(:watched, :scores, :page, :page_size) " +
                    "join movie m using(id)")
    List<Movie> recommend(@Param("watched") String watched,
                          @Param("scores") String scores,
                          @Param("page") int page,
                          @Param("page_size") int pageSize);

    @Query(nativeQuery = true,
            value = "select m.*, r.popularity, r.rating from movie m join rating r using (id) " +
                    "where m.id = :id")
    Optional<Map<String, Object>> getFullInfo(@Param("id") String id);

    @Query(nativeQuery = true,
            value = "select m.*, r.popularity, r.rating from movie m join rating r using (id) " +
                    "where m.id in :ids")
    List<Map<String, Object>> getFullInfoAll(@Param("ids") List<String> ids);
}
