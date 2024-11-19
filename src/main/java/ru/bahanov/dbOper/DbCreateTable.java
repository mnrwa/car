package ru.bahanov.dbOper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCreateTable {
    private static String url;
    private static String user;
    private static String password;

    public static void main(String[] args) {
        loadConfig("src/main/java/ru/bahanov/dbOper/dbConfig.json");

        String createTableSQL = "CREATE TABLE IF NOT EXISTS cars ("
                + "id SERIAL PRIMARY KEY, "
                + "brand VARCHAR(255), "
                + "model_name VARCHAR(255), "
                + "country VARCHAR(25), "
                + "countryCode VARCHAR(255)"
                + ");";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(createTableSQL);
            System.out.println("Таблица cars успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void loadConfig(String configFilePath) {
        try {
            DBConfig dbConfig = DBConfig.loadFromFile(configFilePath);
            url = dbConfig.getUrl();
            user = dbConfig.getUser();
            password = dbConfig.getPassword();
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке конфигурации базы данных: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
