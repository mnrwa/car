package ru.bahanov.dealer;

import ru.bahanov.dto.CarDTO;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DealerCenter {
    private List<CarDTO> cars;
    private List<CarDTO> carsInShowroom;
    private int totalCars;
    private int countShowroomCars;

    public DealerCenter() {
        this.carsInShowroom = new CopyOnWriteArrayList<>();
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
        this.totalCars = cars.size();
    }

    public List<CarDTO> getCarsInShowroom() {
        return carsInShowroom;
    }

    public void setCarsInShowroom(List<CarDTO> carsInShowroom) {
        this.carsInShowroom = carsInShowroom;
        this.countShowroomCars = carsInShowroom.size();
    }

    public int getCountShowroomCars() {
        return countShowroomCars;
    }

    public void setCountShowroomCars(int countShowroomCars) {
        this.countShowroomCars = countShowroomCars;
    }

    public int getTotalCars() {
        return totalCars;
    }

    public void addCarToShowroom(CarDTO car) {
        carsInShowroom.add(car);
        countShowroomCars = carsInShowroom.size();
    }
}
