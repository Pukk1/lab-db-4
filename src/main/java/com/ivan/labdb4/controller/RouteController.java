package com.ivan.labdb4.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class RouteController {

    @GetMapping
    public String getRootPage() {
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/auth/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping("/main")
    public String getMainPage(
            @RequestParam("token") String token,
            Model model
    ) {
        model.addAttribute("token", token);
        return "main";
    }

    @GetMapping("/manage/highlights")
    public String postManagePage(
            @RequestParam("token") String token,
            Model model
    ) {
        model.addAttribute("token", token);
        return "manage-highlights";
    }
}
