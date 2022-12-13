package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Highlight {

    @Id
    private Long id;
    @OneToOne(targetEntity = HighlightAuthor.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private HighlightAuthor author;

    public Highlight(HighlightAuthor author) {
        this.author = author;
    }
}
