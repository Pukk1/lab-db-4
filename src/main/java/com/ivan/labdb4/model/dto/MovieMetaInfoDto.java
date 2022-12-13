package com.ivan.labdb4.model.dto;

import lombok.Data;

@Data
public class MovieMetaInfoDto {
    private Long id;
    private MovieDto movie;
    private String country;
    private String description;
}
