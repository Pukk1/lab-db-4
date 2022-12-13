package com.ivan.labdb4.controller;

import com.ivan.labdb4.model.MovieMetaInfo;
import com.ivan.labdb4.model.dto.MovieMetaInfoDto;
import com.ivan.labdb4.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/movie")
@RequiredArgsConstructor
public class MovieController {

    private final ModelMapper mapper;
    private final MovieService service;

    @GetMapping("/info/{id}")
    public MovieMetaInfoDto getMovieInfo(@PathVariable("id") MovieMetaInfo info) {
        return mapper.map(info, MovieMetaInfoDto.class);
    }
}
