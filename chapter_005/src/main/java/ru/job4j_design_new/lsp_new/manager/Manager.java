package ru.job4j_design_new.lsp_new.manager;

import ru.job4j_design_new.lsp_new.car.Car;
import ru.job4j_design_new.lsp_new.parking.IParking;
import ru.job4j_design_new.lsp_new.service.Service;
import java.util.ArrayList;
import java.util.List;

public class Manager implements IManager {
    private List<IParking> parking;
    private IParking cars;
    private IParking trucks;
    private Service service;

    public Manager(IParking cars, IParking trucks, Service service) {
        this.parking = new ArrayList<>();
        this.service = service;
        this.cars = cars;
        this.trucks = trucks;
        parking.add(cars);
        parking.add(trucks);
    }

    @Override
    public void givePlace(Car car) {
        for (IParking p : parking) {
            if (p.accept(car) && p.freePlaces() > 0) {
                p.add(car, service.getPlace(p.allPlaces()));
                return;
            }
        }
        if (car.isNonStandardParking()) {
            int[] numberPlaces = service.findPlace(car, cars.allPlaces());
            if (numberPlaces != null) {
                for (int place : numberPlaces) {
                    cars.add(car, place);
                }
            }
            car.setSuitableParking(false);
        }
    }

    @Override
    public void takePlace(Car car) {
        if (!car.isSuitableParking()) {
            for (int i = 0; i < car.getSizeNonStandardPlace(); i++) {
                cars.clear(car);
            }
            return;
        }
        for (IParking p : parking) {
            if (p.accept(car)) {
                p.clear(car);
                break;
            }
        }
    }
}
