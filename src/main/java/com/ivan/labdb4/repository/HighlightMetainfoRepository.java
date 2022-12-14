package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighlightMetainfoRepository extends JpaRepository<HighlightMetainfo, Integer> {

    @Query(nativeQuery = true, value = "SELECT highlight_metainfo.id from highlight_metainfo" +
            "                               JOIN highlight on highlight_metainfo.highlight_id = highlight.id" +
            "                               WHERE highlight.author_id = :highLightAuthorId")
    List<Integer> getAllHighLightsIdsOfAuthor(@Param(value = "highLightAuthorId") Integer highLightAuthorId);

    @Query(nativeQuery = true, value = "SELECT id from highlight_metainfo")
    List<Integer> getAllHighLightsMetaInfoIds();
}
