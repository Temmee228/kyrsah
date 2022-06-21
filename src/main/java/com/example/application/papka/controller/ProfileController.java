package com.example.application.papka.controller;

import com.example.application.papka.model.User;
import com.example.application.papka.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ProfileController {

    private UserRepo userRepo;

    public ProfileController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/profile")
    public String profile(Model model, Authentication auth) {
        User user = (User)auth.getPrincipal();
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping("/profile/edit/{id}")
    public String profileEdit (@PathVariable(value = "id") long id, Model model, Authentication auth) {
        User user = (User)auth.getPrincipal();
        if (user.getId() != id) {
            return "redirect:/profile";
        } else {
            model.addAttribute("user", user);
            return "profile-edit";
        }
    }

    @PostMapping("/profile/edit/{id}")
    public String profilePostEdit(@PathVariable(value = "id") long id, @RequestParam String name , @RequestParam String surname, @RequestParam String patronymic, @RequestParam String password, @RequestParam String phone , Model model) {
        User user = userRepo.findById(id).orElseThrow();
        user.setName(name);
        user.setSurname(surname);
        user.setPatronymic(patronymic);
        user.setPhone(phone);
        user.setPassword(password);
        userRepo.save(user);
        return "redirect:/profile";
    }
}