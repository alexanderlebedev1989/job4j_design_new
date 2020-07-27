package ru.job4j_design_new.lsp_new.service;

import ru.job4j_design_new.lsp_new.car.Car;
import ru.job4j_design_new.lsp_new.new_service.ThreeParkingPlaces;
import ru.job4j_design_new.lsp_new.new_service.TwoParkingPlaces;
import ru.job4j_design_new.lsp_new.new_service.Place;
import java.util.ArrayList;
import java.util.List;

public class ServiceFindPlaces implements Service {

    private List<Place> placeList;
    private Place twoPlaces;
    private Place threePlaces;

    public ServiceFindPlaces() {
        placeList = new ArrayList<>();
        twoPlaces = new TwoParkingPlaces();
        threePlaces = new ThreeParkingPlaces();
        placeList.add(twoPlaces);
        placeList.add(threePlaces);
    }

    @Override
    public int getPlace(Car[] cars) {
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
    public int[] findPlace(Car car, Car[] cars) {
        int[] places = new int[0];
        for (Place place : placeList) {
            if (place.sizePlace(car)) {
                places = place.getPlaces(cars);
            }
        }
        return places;
    }
}
