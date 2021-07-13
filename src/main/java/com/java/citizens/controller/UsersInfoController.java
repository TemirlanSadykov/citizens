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
@RequestMapping("/info")
@AllArgsConstructor
public class UsersInfoController {

    private final UserService userService;
    private final PropertiesService propertiesService;
    private final DocumentService documentService;

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
        if (userService.getOne(userDTO.getLogin())==null) {
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

    @GetMapping("/user")
    public ModelAndView getUser(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable) {
        model.addAttribute("userName", principal.getName());

        var users = userService.getAllUsers(Constants.ROLE_USER, pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(users, propertiesService.getDefaultPageSize(), model, uri);


        return new ModelAndView("work/getUsers");
    }

    @GetMapping("/user/open/{id}")
    public ModelAndView openUser(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));

        return new ModelAndView("work/openUser");
    }

    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(Model model, Principal principal, @PathVariable("id") Long id) {
        model.addAttribute("userName", principal.getName());
        userService.deleteUser(id);
    }

    @GetMapping("/user/edit/{id}")
    public ModelAndView editUser(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("id", id);

        return new ModelAndView("work/editUser");
    }

    @GetMapping("/user/edit/info/{id}")
    public ModelAndView editUserInfo(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));

        return new ModelAndView("work/editUserInfo");
    }


    @PutMapping("/user/edit/info/{id}")
    public ModelAndView editUserInfo(@Valid UserDTO userDTO,
                                     BindingResult validationResult, RedirectAttributes attributes, @PathVariable Long id) throws IllegalArgumentException {
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/user/info/edit/info/" + id);
        }
        userService.editUserInfo(userDTO, id);
        return new ModelAndView("redirect:/info/user");
    }

    @GetMapping("/user/edit/document/{id}")
    public ModelAndView editUserDocument(Model model, Principal principal, @PathVariable Long id) {
        model.addAttribute("userName", principal.getName());
        model.addAttribute("user", userService.getOne(userService.getOneById(id).getLogin()));
        model.addAttribute("doctype", documentService.getDocumentType());

        return new ModelAndView("work/editUserDocument");
    }

    @PutMapping("/user/edit/document/{id}")
    public ModelAndView editUserDocument(@Valid DocumentDTO documentDTO,
                                         BindingResult validationResult, RedirectAttributes attributes, @PathVariable Long id) throws IllegalArgumentException {
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return new ModelAndView("redirect:/user/info/edit/document/" + id);
        }
        documentService.editUserDocument(documentDTO, id);
        return new ModelAndView("redirect:/info/user");
    }

    @GetMapping("/user/search")
    public ModelAndView searchUser(Model model, Principal principal) {
        model.addAttribute("userName", principal.getName());
        return new ModelAndView("work/searchUser");
    }

    @PostMapping("/user/search")
    public ModelAndView searchUser(@RequestParam("name") String name, @RequestParam("birth") String birth, @RequestParam("number") String number,
                                   RedirectAttributes attributes) {

        attributes.addFlashAttribute("name", name);
        attributes.addFlashAttribute("birth", birth);
        attributes.addFlashAttribute("number", number);


        return new ModelAndView("redirect:/info/user/search/find");
    }

    @GetMapping("/user/search/find")
    public ModelAndView findUser(Model model, Principal principal, HttpServletRequest uriBuilder, Pageable pageable,
                                 @ModelAttribute("name") String name, @ModelAttribute("birth") String birth, @ModelAttribute("number") String number) {
        model.addAttribute("userName", principal.getName());

        var users = userService.searchUser(name, birth, number, Constants.ROLE_USER, pageable);
        String uri = uriBuilder.getRequestURI();
        PropertiesService.constructPageable(users, propertiesService.getDefaultPageSize(), model, uri);

        return new ModelAndView("work/getUsers");
    }
}