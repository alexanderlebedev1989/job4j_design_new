package ru.job4j_design_new.lsp_new.parking;

import ru.job4j_design_new.lsp_new.car.Vehicle;

public interface IParking {
    boolean add(Vehicle vehicle);
    boolean canPark(Vehicle vehicle);
    Vehicle retrieve(String number);
}
