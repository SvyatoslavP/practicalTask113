package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.service.CarService;

@Controller
public class CarsController {

    @Autowired
    private CarService carService;

    @GetMapping(value = "/cars")
    public String printCars(Model model) {
        model.addAttribute("carsList", carService.getCarList());
        return "cars";
    }
}
