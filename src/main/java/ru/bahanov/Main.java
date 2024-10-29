package ru.bahanov;

import ru.bahanov.dto.CarModelDTO;
import ru.bahanov.service.FileSystemCarModelService;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileSystemCarModelService fileSystemCarModelService = new FileSystemCarModelService();
        try {
            fileSystemCarModelService.load("D:\\prac\\car\\src\\main\\java\\ru\\bahanov\\DST_CAR_MODEL.txt");
            List<CarModelDTO> carModels = fileSystemCarModelService.getCarModels();
            System.out.println(carModels.toString());

        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла: " + e.getMessage());
        }

        System.out.println("Загружено моделей автомобилей: " + fileSystemCarModelService.getAllCar());

        System.out.println("Вывод автомобиля по его id: 3000: " + fileSystemCarModelService.findCarById(3000));
    }
}
