package com.example.application.papka.controller;

import com.example.application.papka.model.Car;
import com.example.application.papka.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Controller
public class CarController {

    private final CarService carService;
    Random random = new Random();
    private final List<String> models = new ArrayList<>(Arrays.asList("BMW", "MERCEDES", "TELEGA", "LADA", "VOLVO", "CHERRY", "ZALUPA"));
    private final List<String> colors = new ArrayList<>(Arrays.asList("red", "green", "blue", "white", "pink", "rainbow", "black"));
    private final List<String> maxSpeeds = new ArrayList<>(Arrays.asList("20 km/h", "80 km/h", "100 km/h", "200 km/h", "120 km/h", "170 km/h", "10 km/h"));

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars/add")
    public String carsAdd(Model model) {

        return "cars-add";
    }

    @PostMapping("/cars/add")
    public String carsAddPost(@RequestParam String model, @RequestParam String color, @RequestParam String max_speed, Model mod) {
        carService.insert(new Car(9999, model, color, max_speed));
        return "redirect:/cars";
    }

    @GetMapping(value = "/cars")
    public String selectAll(Model model) {
        model.addAttribute("car", carService.selectAll());

        return "cars";
    }

    @GetMapping(value = "/cars/allnew")
    public String updateAll(Model model) {
        List<Car> cars = carService.selectAll();
        for (int k = 0; k < 10; k++) {
            cars.add(carService.update(new Car(
                    models.get(random.nextInt(0, 7)),
                    colors.get(random.nextInt(0, 7)),
                    maxSpeeds.get(random.nextInt(0, 7))), k+1));
            model.addAttribute(String.format("newcar%s", k+1), cars.get(k));
        }

        return "newcars";
    }


    @GetMapping(value = "/cars/more/{id}")
    public String carsGet (@PathVariable(value = "id") int id, Model model) {
        if(carService.select(id) == null) {
            return "redirect:/cars";
        }
        else  {
            model.addAttribute("cars", carService.select(id));
            return "cars-more";
        }
    }

    @GetMapping("/cars/delete/{id}")
    public String carsDelete (@PathVariable(value = "id") int id, Model model) {
        carService.delete(id);
        return "redirect:/cars";
    }

    @GetMapping("/cars/edit/{id}")
    public String carsEdit (@PathVariable(value = "id") Integer id, Model model) {
        model.addAttribute("cars", carService.select(id));
        return "cars-edit";
    }

    @PostMapping ("/cars/edit/{id}")
    public String carsEditPost(@PathVariable(value = "id") Integer id, @RequestParam String model,
                              @RequestParam String color, @RequestParam String max_speed, Model mod) {
        carService.update(new Car(model, color, max_speed), id);
        return "redirect:/cars";
    }
}