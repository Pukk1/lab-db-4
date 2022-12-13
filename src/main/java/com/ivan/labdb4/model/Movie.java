package com.ivan.labdb4.model;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String genre;
    private Float rating;
    private Integer ageRating;
}
