import ru.bahanov.dto.CarDTO;
import ru.bahanov.dealer.DealerService;
import ru.bahanov.dealer.DealerCenter;

import org.junit.Test;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DealerCenterTest {

    @Test
    public void testMultiThreads() throws InterruptedException {
        DealerService dealerService = new DealerService();
        DealerCenter dealerCenter = new DealerCenter();

        List<CarDTO> carList = generateCars(10000);
        dealerCenter.setCars(new CopyOnWriteArrayList<>(carList));
        dealerCenter.setCarsInShowroom(new CopyOnWriteArrayList<>());

        List<CarDTO> part1 = carList.subList(0, 3333);
        List<CarDTO> part2 = carList.subList(3333, 6666);
        List<CarDTO> part3 = carList.subList(6666, 10000);

        Thread t1 = new Thread(() -> dealerService.processCarList(dealerCenter, part1));
        Thread t2 = new Thread(() -> dealerService.processCarList(dealerCenter, part2));
        Thread t3 = new Thread(() -> dealerService.processCarList(dealerCenter, part3));

        long startTime = System.currentTimeMillis();

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTime = System.currentTimeMillis();

        System.out.println("Общее количество автомобилей в шоуруме: " + dealerCenter.getCountShowroomCars());
        System.out.println("Время обработки: " + (endTime - startTime) + " мс");
    }

    private List<CarDTO> generateCars(int count) {
        List<CarDTO> cars = new CopyOnWriteArrayList<>();
        for (int i = 0; i < count; i++) {
            cars.add(new CarDTO("BMW", "X5", "SuperPremium", "Black"));
            cars.add(new CarDTO("Mazda", "q6", "Standard", "White"));
            cars.add(new CarDTO("Toyota", "Camry", "Premium", "Black"));
            cars.add(new CarDTO("Volvo", "XC90", "SuperPremium", "Red"));

        }
        return cars;
    }
}
