package ru.bahanov.dealer;

import ru.bahanov.dto.CarDTO;
import java.util.List;

public class DealerService {

    public void processCarList(DealerCenter dealer, List<CarDTO> cars) {
        for (CarDTO car : cars) {
            processCar(dealer, car);
        }
    }

    private void processCar(DealerCenter dealer, CarDTO car) {
        if (isNeedAddToShowroom(car)) {
            addToShowroomCars(dealer, car);
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void addToShowroomCars(DealerCenter dealer, CarDTO car) {
        synchronized (dealer) {
            dealer.addCarToShowroom(car);
        }
    }

    private boolean isNeedAddToShowroom(CarDTO car) {
        boolean shouldAdd = "BMW".equals(car.getBrand()) && "X5".equals(car.getModel()) && "SuperPremium".equals(car.getComplectation());
        System.out.println("Проверка автомобиля " + car.getBrand() + " " + car.getModel() + ": " + shouldAdd);
        return shouldAdd;
    }
}
