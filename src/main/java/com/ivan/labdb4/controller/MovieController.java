package com.ivan.labdb4.controller;

import com.ivan.labdb4.model.MovieMetaInfo;
import com.ivan.labdb4.model.dto.MovieMetaInfoDto;
import com.ivan.labdb4.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movie-info")
@RequiredArgsConstructor
public class MovieController {

    private final ModelMapper mapper;
    private final MovieService service;

    @GetMapping("/{id}")
    public String getMovieInfo(
            @PathVariable("id") MovieMetaInfo info,
            @RequestParam("token") String token,
            Model model
    ) {
        model.addAttribute("token", token);
        model.addAttribute("movieInfo", mapper.map(info, MovieMetaInfoDto.class));
        return "movie-info";
    }

//    @GetMapping("/by-highlight/{id}")
//    public String getMovieInfo(
//            @PathVariable("id") HighlightMetainfo info,
//            @RequestParam("token") String token
//    ) {
//        return "redirect:/movie-info/" + info.getMovie().getMovieMetaInfo().getId() + "?token=" + token;
//    }
}
