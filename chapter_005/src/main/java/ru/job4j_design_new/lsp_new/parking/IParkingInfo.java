package ru.job4j_design_new.lsp_new.parking;

import ru.job4j_design_new.lsp_new.car.Car;

public interface IParkingInfo {
    int freePlaces();
    Car[] allPlaces();
}
