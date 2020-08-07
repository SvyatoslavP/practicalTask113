package web.service;

import web.model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarService {
    private List<Car> carList = new ArrayList<>();
    {
        carList.add(new Car("Toyota", "Corolla", 123));
        carList.add(new Car("Honda", "Jazz", 456));
        carList.add(new Car("Nissan", "AD", 789));
    }

    public List<Car> getAllCar() {
        return carList;
    }
}
