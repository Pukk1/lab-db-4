package com.ivan.labdb4.controller;

import com.ivan.labdb4.jwt.JwtTokenProvider;
import com.ivan.labdb4.model.HighlightMetainfo;
import com.ivan.labdb4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/like-video")
    public String likeVideo(
            @RequestParam("video-info-id") HighlightMetainfo videoInfo,
            @RequestParam("token") String token
    ) {
        customerService.likeVideo(jwtTokenProvider.getUsername(token), videoInfo);
        return "redirect:/movie-info/" + videoInfo.getMovie().getId() + "&token=" + token;
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
