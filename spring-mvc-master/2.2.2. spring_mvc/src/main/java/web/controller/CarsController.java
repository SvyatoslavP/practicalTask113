package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.Car;
import web.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarsController {

    @Autowired
    private CarService carService;

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public CarService getCarService() {
        return carService;
    }

    @GetMapping(value = "/cars")
    public String printCars(Model model) {
        List<Car> carList = new ArrayList<>();
        carList.add(new Car("Toyota", "Corolla", 123));
        carList.add(new Car("Honda", "Jazz", 456));
        carList.add(new Car("Nissan", "AD", 789));
        carService.setCarList(carList);
        model.addAttribute("carsList", carService.getCarList());
        return "cars";
    }
}
