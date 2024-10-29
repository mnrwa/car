package ru.bahanov.dto;

import java.util.Objects;

public class CarModelDTO {
    private final int id;
    private final String brand;
    private final String modelName;
    private final String country;
    private final String countryCode;

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

    public boolean equalsByFields(CarModelDTO other) {
        return this.brand.equals(other.brand) &&
                this.modelName.equals(other.modelName);
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
}
