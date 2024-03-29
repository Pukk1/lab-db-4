package com.ivan.labdb4.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Highlight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(targetEntity = HighlightAuthor.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private HighlightAuthor author;
    private String name;

    public Highlight(HighlightAuthor author, String name) {
        this.author = author;
        this.name = name;
    }
}
