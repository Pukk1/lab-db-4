package com.ivan.labdb4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MovieMetaInfo {
    @Id
    private Integer id;
    @OneToOne(targetEntity = Movie.class, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    private Movie movie;
    private String country;
    private String description;
}
