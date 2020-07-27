package ru.job4j_design_new.lsp_new.new_service;

import ru.job4j_design_new.lsp_new.car.Car;

public interface Place {

    boolean sizePlace(Car car);

    int[] getPlaces(Car[] cars);

}
