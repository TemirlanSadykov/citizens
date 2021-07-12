package com.java.citizens.controller;

import com.java.citizens.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user")
    public ModelAndView userPage(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
            model.addAttribute("user", userService.getOne(principal.getName()));
            return new ModelAndView("users/user");
    }

    @GetMapping("/admin")
    public ModelAndView adminPage(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return new ModelAndView("users/admin");
    }

    @GetMapping("/error")
    public ModelAndView errorPage() {
        return new ModelAndView("users/error");
    }
}
