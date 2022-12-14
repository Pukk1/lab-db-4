package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.MovieMetaInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieMetaInfoRepository extends CrudRepository<MovieMetaInfo, Integer> {
    @Query(value = "select i from MovieMetaInfo i where i.movie.id = :film_id")
    List<MovieMetaInfo> findMovieMetaInfoByMovieId(@Param("film_id") Integer movieId);
}
