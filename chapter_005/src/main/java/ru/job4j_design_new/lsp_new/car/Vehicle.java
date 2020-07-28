package ru.job4j_design_new.lsp_new.car;

import java.util.Objects;

public class Vehicle implements IVehicle {
    private String name;
    private String number;
    private int size;

    public Vehicle(String name, String number, int size) {
        this.name = name;
        this.number = number;
        this.size = size;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String number() {
        return number;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(number, vehicle.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
