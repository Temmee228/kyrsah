package com.example.application.papka.controller;

import com.example.application.papka.model.Car;
import com.example.application.papka.service.CarServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MainController {

    private final CarServiceImpl carService;

    public MainController(CarServiceImpl carService) {
        this.carService = carService;
    }


    @GetMapping("/")
    public String home(Model model) {
        List<Car> cars = carService.getAll();
        model.addAttribute("cars", cars.get(0));
        return "home";
    }
}
