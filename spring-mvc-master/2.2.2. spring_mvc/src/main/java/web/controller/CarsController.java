package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.CarService;

@Controller
public class CarsController {

    @GetMapping(value = "/cars")
    public String printCars(Model model) {
        CarService carService = new CarService();
        model.addAttribute("carsList", carService.getAllCar());
        return "cars";
    }
}
