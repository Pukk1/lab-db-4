package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.HighlightAuthor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HighlightAuthorRepository extends JpaRepository<HighlightAuthor, Integer> {

    HighlightAuthor findByUsername(String username);
}
