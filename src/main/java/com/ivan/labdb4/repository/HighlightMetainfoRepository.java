package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighlightMetainfoRepository extends JpaRepository<HighlightMetainfo, Long> {

    @Query(nativeQuery = true, value = "SELECT id from highlight_metainfo")
    List<Long> getAllHighLightsIds();
}
