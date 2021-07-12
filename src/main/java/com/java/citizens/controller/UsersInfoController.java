package com.java.citizens.controller;

import com.java.citizens.dto.DocumentDTO;
import com.java.citizens.dto.UserDTO;
import com.java.citizens.service.DocumentService;
import com.java.citizens.service.PropertiesService;
import com.java.citizens.service.UserService;
import com.java.citizens.util.Constants;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/info/user")
@AllArgsConstructor
public class UsersInfoController {

    private final UserService userService;
    private final PropertiesService propertiesService;
    private final DocumentService documentService;

    @GetMapping
    public ModelAndView getUser(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable) {
        model.addAttribute("userName", principal.getName());

        var users = userService.getAllUsers(Constants.ROLE_USER, pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(users, propertiesService.getDefaultPageSize(), model, uri);


        return new ModelAndView("work/getUsers");
    }

    @GetMapping("/open/{id}")
    public ModelAndView openUser(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));

        return new ModelAndView("work/openUser");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(Model model, Principal principal, @PathVariable("id") Long id) {
        model.addAttribute("userName", principal.getName());
        userService.deleteUser(id);
    }

    @GetMapping("/edit/{id}")
    public ModelAndView editUser(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("id", id);

        return new ModelAndView("work/editUser");
    }

    @GetMapping("/edit/info/{id}")
    public ModelAndView editUserInfo(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));

        return new ModelAndView("work/editUserInfo");
    }


    @PutMapping("/edit/info/{id}")
    public ModelAndView editUserInfo(@Valid UserDTO userDTO,
                                     BindingResult validationResult, RedirectAttributes attributes, @PathVariable Long id) throws IllegalArgumentException {
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/user/info/edit/info/" + id);
        }
        userService.editUserInfo(userDTO, id);
        return new ModelAndView("redirect:/info/user");
    }

    @GetMapping("/edit/document/{id}")
    public ModelAndView editUserDocument(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));
        model.addAttribute("doctype", documentService.getDocumentType());

        return new ModelAndView("work/editUserDocument");
    }

    @PutMapping("/edit/document/{id}")
    public ModelAndView editUserDocument(@Valid DocumentDTO documentDTO,
                                         BindingResult validationResult, RedirectAttributes attributes, @PathVariable Long id) throws IllegalArgumentException {
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/user/info/edit/document/" + id);
        }
        documentService.editUserDocument(documentDTO, id);
        return new ModelAndView("redirect:/info/user");
    }

    @GetMapping("/search")
    public ModelAndView searchUser(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return new ModelAndView("work/searchUser");
    }

    @PostMapping("/search")
    public ModelAndView searchUser(@RequestParam("name") String name, @RequestParam("birth") String birth, @RequestParam("number") String number,
                                   RedirectAttributes attributes) {

        attributes.addFlashAttribute("name", name);
        attributes.addFlashAttribute("birth", birth);
        attributes.addFlashAttribute("number", number);


        return new ModelAndView("redirect:/info/user/search/find");
    }

    @GetMapping("/search/find")
    public ModelAndView findUser(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable,
                                 @ModelAttribute("name") String name, @ModelAttribute("birth") String birth, @ModelAttribute("number") String number) {
        model.addAttribute("userName", principal.getName());

        var users = userService.searchUser(name, birth, number, Constants.ROLE_USER, pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(users, propertiesService.getDefaultPageSize(), model, uri);

        return new ModelAndView("work/getUsers");
    }
}