package com.ivan.labdb4.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Movie {
    @Id
    private Long id;
    private String name;
    private String genre;
    private Float rating;
    private Integer ageRating;
}
