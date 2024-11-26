package ru.bahanov;

import ru.bahanov.dbOper.DBConfig;
import ru.bahanov.dbOper.DbPrintCar;
import ru.bahanov.dealer.DealerCenter;
import ru.bahanov.dealer.DealerService;
import ru.bahanov.dto.CarDTO;
import ru.bahanov.dto.CarModelDTO;
import ru.bahanov.service.FileSystemCarModelService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main {
    public static void main(String[] args) {
        DBConfig dbConfig = null;
        try {
            dbConfig = DBConfig.loadFromFile("src/main/java/ru/bahanov/dbOper/dbConfig.json");
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке конфигурации базы данных: " + e.getMessage());
            return;
        }

        FileSystemCarModelService fileSystemCarModelService = new FileSystemCarModelService();

        try {
            fileSystemCarModelService.load("D:\\prac\\car\\src\\main\\java\\ru\\bahanov\\DST_CAR_MODEL.txt");
            List<CarModelDTO> carModels = fileSystemCarModelService.getCarModels();

            System.out.println("Загружено моделей автомобилей: " + fileSystemCarModelService.getAllCar());

            CarModelDTO carById = fileSystemCarModelService.findCarById(3000);
            System.out.println("Вывод автомобиля по его id 3000: " + (carById != null ? carById : "Не найдено"));

            List<CarModelDTO> volvoCars = fileSystemCarModelService.findCarByBrand("Volvo");
            System.out.println("Вывод автомобилей по марке 'Volvo': " + volvoCars.size());

            int idToPrint = 15;
            DbPrintCar carModelPrinter = new DbPrintCar(fileSystemCarModelService);
            carModelPrinter.printCarById(idToPrint);

        } catch (IOException e) {
            System.err.println("Ошибка загрузки файла: " + e.getMessage());
        }
    }

}
