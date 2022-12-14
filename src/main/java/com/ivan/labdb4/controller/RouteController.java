package com.ivan.labdb4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class RouteController {

    private final AuthController authController;

    @GetMapping
    public String getRootPage() {
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/main")
    public String postMainPage(
            @RequestParam("uid") Integer uid,
            @RequestHeader("Authorization") String token,
            Model model
    ) {
        model.addAttribute("uid", uid);
        model.addAttribute("token", token);
        return "main";
    }
}
