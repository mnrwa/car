package ru.bahanov.dto;

public class CarDTO {
    private String brand;
    private String model;
    private String complectation;
    private String color;

    public CarDTO(String brand, String model, String complectation, String color) {
        this.brand = brand;
        this.model = model;
        this.complectation = complectation;
        this.color = color;
    }

    public CarDTO(CarModelDTO carModel) {
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getColor() {
        return color;
    }

    public String getComplectation() {
        return complectation;
    }
}

