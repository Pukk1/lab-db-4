package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.HighlightMetainfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighlightMetainfoRepository extends JpaRepository<HighlightMetainfo, Long> {

}
