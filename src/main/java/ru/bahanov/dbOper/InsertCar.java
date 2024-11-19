package ru.bahanov.dbOper;

import ru.bahanov.dto.CarModelDTO;
import ru.bahanov.service.FileSystemCarModelService;

import java.io.IOException;
import java.sql.*;
import java.util.List;

public class InsertCar {
    private static DBConfig dbConfig;
    private static FileSystemCarModelService load = new FileSystemCarModelService();

    static {
        try {
            dbConfig = DBConfig.loadFromFile("src/main/java/ru/bahanov/dbOper/dbConfig.json");
            load.load("D:\\prac\\car\\src\\main\\java\\ru\\bahanov\\DST_CAR_MODEL.txt");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке данных из файла или конфигурации", e);
        }
    }

    public static void main(String[] args) {
        List<CarModelDTO> carModels = load.getCarModels();
        InsertCar insertCar = new InsertCar();

        try {
            insertCar.insertCarsBatch(carModels);
            System.out.println("Данные успешно добавлены в таблицу!");

            insertCar.printCarsFromDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCarsBatch(List<CarModelDTO> carModels) throws SQLException {
        String query = "INSERT INTO cars (id, brand, model_name, country, countryCode) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword())) {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                for (CarModelDTO carModel : carModels) {
                    preparedStatement.setInt(1, carModel.getId());
                    preparedStatement.setString(2, carModel.getBrand());
                    preparedStatement.setString(3, carModel.getModelName());
                    preparedStatement.setString(4, carModel.getCountry());
                    preparedStatement.setString(5, carModel.getCountryCode());

                    preparedStatement.addBatch();
                }

                int[] results = preparedStatement.executeBatch();
                connection.commit();

                System.out.println("Вставлено записей: " + results.length);
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }
        }
    }

    public void printCarsFromDatabase() {
        String query = "SELECT * FROM cars";

        try (Connection connection = DriverManager.getConnection(dbConfig.getUrl(), dbConfig.getUser(), dbConfig.getPassword());
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("id") +
                        ", Brand: " + resultSet.getString("brand") +
                        ", Model: " + resultSet.getString("model_name") +
                        ", Country: " + resultSet.getString("country") +
                        ", Country Code: " + resultSet.getString("countryCode"));
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при чтении данных из базы: " + e.getMessage());
        }
    }
}
