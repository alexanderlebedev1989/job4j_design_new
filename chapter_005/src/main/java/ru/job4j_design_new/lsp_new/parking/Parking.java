package ru.job4j_design_new.lsp_new.parking;

import ru.job4j_design_new.lsp_new.car.Car;

public class Parking {
    private Car[] cars;
    private int freePlaces;
    private int countPlaces;

    private final double MAX_WEIGHT_CAR = 1.5;
    private double MAX_WEIGHT_TRUCK = 3;

    public Parking(int countPlaces) {
        this.cars = new Car[countPlaces];
        this.freePlaces = countPlaces;
    }

    public Car[] getCars() {
        return cars;
    }

    public double getMAX_WEIGHT_CAR() {
        return MAX_WEIGHT_CAR;
    }

    public double getMAX_WEIGHT_TRUCK() {
        return MAX_WEIGHT_TRUCK;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }
}
