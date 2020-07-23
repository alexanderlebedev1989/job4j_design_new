package ru.job4j_design_new.lsp_new.service;

import ru.job4j_design_new.lsp_new.car.Car;

public interface Service {

    int standardPlace(Car[] cars);
    int[] nonStandardPlace(Car[] cars);
}
