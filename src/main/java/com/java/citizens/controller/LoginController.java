package com.java.citizens.controller;

import com.java.citizens.service.RoleService;
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
public class LoginController {

    private final RoleService roleService;

    @GetMapping("/default")
    public ModelAndView defaultPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model, Principal principal) {
        model.addAttribute("error", error);

        return new ModelAndView(roleService.giveRoles(principal));
    }

    @GetMapping("/")
    public ModelAndView login(Model model, @RequestParam(required = false, defaultValue = "false") Boolean error) {
        model.addAttribute("error", error);
        return new ModelAndView("login");
    }

}