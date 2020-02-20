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
            value = "select m.*, r.popularity, r.rating, p.pic_uri "
                    + "from search_movies(:pattern, :page, :page_size) "
                    + "left join movie m using (id) "
                    + "left join rating r using (id) "
                    + "left join picture p using (id)"
    )
    List<Map<String, Object>> search(@Param("pattern") String pattern,
                                     @Param("page") int page,
                                     @Param("page_size") int pageSize);

    @Query(nativeQuery = true,
            value = "select m.*, r.popularity, r.rating, p.pic_uri " +
                    "from recommended_movies(:watched, :scores, :page, :page_size) " +
                    "left join movie m using (id) " +
                    "left join rating r using (id) " +
                    "left join picture p using (id)")
    List<Map<String, Object>> recommend(@Param("watched") String watched,
                                        @Param("scores") String scores,
                                        @Param("page") int page,
                                        @Param("page_size") int pageSize);

    @Query(nativeQuery = true,
            value = "select m.*, r.popularity, r.rating, p.pic_uri " +
                    "from movie m " +
                    "left join rating r using (id) " +
                    "left join picture p using (id)" +
                    "where m.id = :id")
    Optional<Map<String, Object>> getFullInfo(@Param("id") String id);

    @Query(nativeQuery = true,
            value = "select m.*, r.popularity, r.rating, p.pic_uri " +
                    "from movie m " +
                    "left join rating r using (id) " +
                    "left join picture p using (id)" +
                    "where m.id in :ids")
    List<Map<String, Object>> getFullInfoAll(@Param("ids") List<String> ids);
}
