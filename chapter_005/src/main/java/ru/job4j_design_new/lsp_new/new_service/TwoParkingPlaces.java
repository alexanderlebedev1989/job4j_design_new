package ru.job4j_design_new.lsp_new.new_service;

import ru.job4j_design_new.lsp_new.car.Car;

public class TwoParkingPlaces implements Place {

    private final int SIZE_PLACE = 2;

    @Override
    public boolean sizePlace(Car car) {
        return car.getSizeNonStandardPlace() == SIZE_PLACE;
    }

    @Override
    public int[] getPlaces(Car[] cars) {
        int[] places = new int[SIZE_PLACE];
        for (int i = 0; i < cars.length; i++) {
            if (cars[i] == null && (cars[i + 1] == null && i + i < cars.length)) {
                for (int index = 0; index < places.length; index++) {
                    places[index] = i++;
                }
                return places;
            }
        }
        return null;
    }
}
