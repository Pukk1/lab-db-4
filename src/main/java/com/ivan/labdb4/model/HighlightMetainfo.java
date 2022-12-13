package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class HighlightMetainfo {
    @Id
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
}
