package com.java.citizens.controller;

import com.java.citizens.dto.DocumentDTO;
import com.java.citizens.dto.UserDTO;
import com.java.citizens.entity.QUser;
import com.java.citizens.repository.UserRepo;
import com.java.citizens.service.DocumentService;
import com.java.citizens.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/info")
@AllArgsConstructor
public class AdminInfoController {

    private final UserService userService;
    private final DocumentService documentService;
    private final UserRepo userRepo;

    @GetMapping
    public ModelAndView info(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(principal.getName()));
        return new ModelAndView("work/info");
    }

    @GetMapping("/createUser")
    public ModelAndView createUser(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("roles", userService.getRoles());
        return new ModelAndView("work/createUser");
    }

    @PostMapping("/createUser")
    public ModelAndView register(@Valid UserDTO userDTO,
                                 BindingResult validationResult, RedirectAttributes attributes) {

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/info/createUser");
        }
        if (userRepo.findOne(QUser.user.login.eq(userDTO.getLogin())).isEmpty()) {
            userService.createUser(userDTO);
            attributes.addFlashAttribute("login", userDTO.getLogin());
            return new ModelAndView("redirect:/info/document");
        } else {
            attributes.addFlashAttribute("error", "Данный пользователь уже зарегистрирован.");
            return new ModelAndView("redirect:/info/createUser");
        }

    }

    @GetMapping("/document")
    public ModelAndView document(Model model, Principal principal, @ModelAttribute("login") String login) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("doctype", documentService.getDocumentType());
        model.addAttribute("login", login);
        return new ModelAndView("work/document");
    }

    @PostMapping("/document")
    public ModelAndView document(@Valid DocumentDTO documentDTO,
                                 BindingResult validationResult, RedirectAttributes attributes) {
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/info/document");
        }
        return documentService.createDocument(documentDTO, attributes);
    }
}
