package com.ivan.labdb4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class MovieMetaInfo {
    @Id
    private Long id;
    @OneToOne(targetEntity = Movie.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Movie movie;
    private String country;
    private String description;
}
