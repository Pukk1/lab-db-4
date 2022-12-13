package com.ivan.labdb4.model.dto;

import lombok.Data;

@Data
public class MovieDto {
    private Long id;
    private String name;
    private String genre;
    private Float rating;
    private Integer ageRating;
}
