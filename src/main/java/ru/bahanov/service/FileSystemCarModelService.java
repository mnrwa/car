package ru.bahanov.service;

import ru.bahanov.dto.CarModelDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FileSystemCarModelService {
    private final List<CarModelDTO> carModels = new ArrayList<>();

    public int getAllCar() {
        return carModels.size();
    }

    public List<CarModelDTO> getCarModels() {
        return carModels;
    }

    public CarModelDTO findCarById(int id) {
        for (CarModelDTO car : carModels) {
            if (car.getId() == id) {
                return car;
            }
        }
        return null;
    }

    public List<CarModelDTO> findCarByBrand(String brand) {
        List<CarModelDTO> carsByBrand = new ArrayList<>();
        for (CarModelDTO car : carModels) {
            if (car.getBrand().equals(brand)) {
                carsByBrand.add(car);
            }
        }
        return carsByBrand;
    }

    public void load(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            int idCounter = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 4) {
                    CarModelDTO carModel = new CarModelDTO(
                            idCounter++,
                            parts[0],
                            parts[1],
                            parts[2],
                            parts[3]
                    );
                    carModels.add(carModel);
                }
            }
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла " + fileName, e);
        }
    }
}
