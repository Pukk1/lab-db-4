package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HighlightRepository extends JpaRepository<Highlight, Integer> {

    @Query(nativeQuery = true, value = "SELECT name from highlight where id = :id")
    String getNameById(@Param(value = "id") Integer highLightId);
}
