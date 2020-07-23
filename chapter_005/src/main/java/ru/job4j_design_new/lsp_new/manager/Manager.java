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
        for (IParking p: parking) {
            if (p.accept(car) && p.freePlaces() > 0) {
                p.add(car, service.standardPlace(p.allPlaces()));
            } else if (p.accept(car)) {
                int[] array = service.nonStandardPlace(cars.allPlaces());
                if (array[0] != -1) {
                    for (int place : array) {
                        cars.add(car, place);
                    }
                }
                car.setStandardPlace(false);
            }
        }
    }

    @Override
    public void takePlace(Car car) {
        for (IParking p : parking) {
            if (!car.isStandardPlace()) {
                cars.clear(car);
                cars.clear(car);
                break;
            }
            else if (p.accept(car)) {
                p.clear(car);
                break;
            }
        }
    }
}
