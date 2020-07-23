package ru.job4j_design_new.lsp_new.service;

import ru.job4j_design_new.lsp_new.car.Car;

public class ServiceFindPlace implements Service {

    private final int NON_STANDARD_PLACE = 2;


    @Override
    public int standardPlace(Car[] cars) {
        int placeNumber = 0;
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null) {
                placeNumber = i;
                break;
            }
        }
        return placeNumber;
    }

    @Override
    public int[] nonStandardPlace(Car[] cars) {
        int[] places = new int[NON_STANDARD_PLACE];
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null && (cars[i + 1] == null && i + i < cars.length)) {
                places[0] = i;
                places[1] = i + 1;
                break;
            }
            places[0] = -1;
        }
        return places;
    }
}
