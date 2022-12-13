package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
