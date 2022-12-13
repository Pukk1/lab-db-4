package com.ivan.labdb4.controller;

import com.ivan.labdb4.model.Customer;
import com.ivan.labdb4.model.HighlightAuthor;
import com.ivan.labdb4.model.HighlightMetainfo;
import com.ivan.labdb4.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/like-video")
    public void likeVideo(@RequestParam("customer-id") Customer customer, @RequestParam("video-info") HighlightMetainfo videoInfo) {
        customerService.likeVideo(customer, videoInfo);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestParam("customer-id") Customer customer, @RequestParam("author_id") HighlightAuthor author) {
        customerService.subscribe(customer, author);
    }
}
