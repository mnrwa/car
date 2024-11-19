package ru.bahanov.dbOper;

import ru.bahanov.dto.CarModelDTO;
import ru.bahanov.service.FileSystemCarModelService;

import java.io.IOException;
import java.sql.*;


public class DbPrintCar {

    private FileSystemCarModelService carModelService;

    public DbPrintCar(FileSystemCarModelService carModelService) {
        this.carModelService = carModelService;
    }

    public void printCarById(int id) {
        CarModelDTO car = carModelService.findCarById(id);
        if (car != null) {
            System.out.println("Вывод автомобиля по id = " + id + ":");
            System.out.println("id: " + car.getId());
            System.out.println("brand: " + car.getBrand());
            System.out.println("modelName: " + car.getModelName());
            System.out.println("country: " + car.getCountry());
            System.out.println("countryCode: " + car.getCountryCode());
        } else {
            System.out.println("Машина с id = " + id + " не найдена.");
        }
    }
}
