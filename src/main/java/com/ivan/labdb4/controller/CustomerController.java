package com.ivan.labdb4.controller;

import com.ivan.labdb4.jwt.JwtTokenProvider;
import com.ivan.labdb4.model.HighlightMetainfo;
import com.ivan.labdb4.repository.MovieMetaInfoRepository;
import com.ivan.labdb4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;
    private final MovieMetaInfoRepository metaInfoRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/like-video/{video-info-id}")
    public String likeVideo(
            @PathVariable("video-info-id") HighlightMetainfo videoInfo,
            @RequestParam("token") String token
    ) {
        customerService.likeVideo(jwtTokenProvider.getUsername(token), videoInfo);
        var movieMetaInfoByMovie = metaInfoRepository.findMovieMetaInfoByMovieId(videoInfo.getMovie().getId()).stream().findFirst().orElseThrow(RuntimeException::new);
        return "redirect:/movie-info/" + movieMetaInfoByMovie.getId() + "?token=" + token;
    }

    @PostMapping("/subscribe")
    public String subscribe(
            @RequestParam("video-info-id") HighlightMetainfo videoInfo,
            @RequestParam("token") String token
    ) {
        customerService.subscribe(jwtTokenProvider.getUsername(token), videoInfo.getHighlight().getAuthor());
        return "redirect:/movie-info/" + videoInfo.getMovie().getId() + "&token=" + token;
    }
}
