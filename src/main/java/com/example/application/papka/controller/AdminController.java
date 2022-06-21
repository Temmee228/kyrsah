package com.example.application.papka.controller;

import com.example.application.papka.model.Position;
import com.example.application.papka.model.User;
import com.example.application.papka.repository.PositionRepo;
import com.example.application.papka.repository.UserRepo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AdminController {

    private UserRepo userRepo;

    private PositionRepo repo;

    public AdminController(UserRepo userRepo, PositionRepo repo) {
        this.userRepo = userRepo;
        this.repo = repo;
    }

    @GetMapping("/admin")
    public String admin(Model model, Authentication auth) {
        User user = (User)auth.getPrincipal();
        model.addAttribute("user", user);
        return "admin";
    }

    @GetMapping("/admin/add")
    public String profileEdit (Model model, Authentication auth) {
        return "admin-add";
    }

    @PostMapping("/admin/add")
    public String profilePostEdit(@RequestParam String username , @RequestParam String password, @RequestParam Long id, Model model) {
        Position position = repo.findById(id).orElseThrow();
        User user = new User(username, password, position);//nikita gay
        userRepo.save(user);
        return "redirect:/admin";
    }

}
