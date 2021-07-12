package com.java.citizens.controller;

import com.java.citizens.service.UserService;
import com.java.citizens.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ModelAndView login(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error) {
        model.addAttribute("error", error);
        return new ModelAndView("login");
    }

    @GetMapping("/user")
    public ModelAndView userPage(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        if(userService.getOne(principal.getName()).getRoleDTO().getName().equals(Constants.ROLE_ADMIN)){
            return new ModelAndView("users/admin");
        }
        model.addAttribute("user", userService.getOne(principal.getName()));
        return new ModelAndView("users/user");
    }

    @GetMapping("/error")
    public ModelAndView errorPage() {
        return new ModelAndView("users/error");
    }
}
