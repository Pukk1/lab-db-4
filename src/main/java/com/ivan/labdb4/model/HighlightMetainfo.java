package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class HighlightMetainfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer duration;
    @OneToOne(targetEntity = Highlight.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "highlight_id", referencedColumnName = "id")
    private Highlight highlight;

    @OneToOne(targetEntity = Movie.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @Lob
    private byte[] data;

    public HighlightMetainfo(Highlight highlight, Movie movie, Integer duration) {
        this.highlight = highlight;
        this.movie = movie;
        this.duration = duration;
    }
}
