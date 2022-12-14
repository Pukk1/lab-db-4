package com.ivan.labdb4.repository;

import com.ivan.labdb4.model.HighlightAuthor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface HighlightAuthorRepository extends JpaRepository<HighlightAuthor, Integer> {

    HighlightAuthor findByUsername(String username);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "INSERT INTO highlight_author(id, nickname) VALUES (:id, :nickname)")
    void persist(@Param(value = "id") Integer customerId, @Param(value = "nickname") String nickname);
}
