package ru.bahanov.service;

import ru.bahanov.dto.CarModelDTO;

import java.io.*;
import java.util.*;

public class FileSystemCarModelService {
    private final Map<Integer, CarModelDTO> carModelsById = new HashMap<>();

    public int getAllCar() {
        return carModelsById.size();
    }

    public List<CarModelDTO> getCarModels() {
        return new ArrayList<>(carModelsById.values());
    }

    public CarModelDTO findCarById(int id) {
        return carModelsById.get(id);
    }

    public List<CarModelDTO> findCarByBrand(String brand) {
        List<CarModelDTO> carsByBrand = new ArrayList<>();
        for (CarModelDTO car : carModelsById.values()) {
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
                    carModelsById.put(carModel.getId(), carModel);
                }
            }
        } catch (IOException e) {
            throw new IOException("Ошибка при чтении файла " + fileName, e);
        }
    }
}
