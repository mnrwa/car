package ru.bahanov.dto;

import java.util.Objects;

public class CarModelDTO {
    private final int id;
    private final String brand;
    private final String modelName;
    private final String country;
    private final String countryCode;
    private  String color;
    private  String complectation;

    public CarModelDTO(int id, String brand, String modelName, String country, String countryCode) {
        this.id = id;
        this.brand = brand;
        this.modelName = modelName;
        this.country = country;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "CarModelDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", modelName='" + modelName + '\'' +
                ", country='" + country + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CarModelDTO)) return false;
        CarModelDTO that = (CarModelDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModelName() {
        return modelName;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getColor() {
        return color;
    }

    public String getComplectation() {
        return complectation;
    }
}
