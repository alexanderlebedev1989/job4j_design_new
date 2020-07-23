package ru.job4j_design_new.lsp_new.parking;

import ru.job4j_design_new.lsp_new.car.Car;

public class ParkingTrucks extends Parking implements IParking {


    public ParkingTrucks(int countPlaces) {
        super(countPlaces);
    }

    @Override
    public boolean accept(Car car) {
        return car.getWeight() >= getMAX_WEIGHT_CAR()
                && car.getWeight() <= getMAX_WEIGHT_TRUCK();
    }

    @Override
    public void add(Car car, int numberPlace) {
        getCars()[numberPlace] = car;
        setFreePlaces(getFreePlaces() - 1);
    }

    @Override
    public void clear(Car car) {
        for (int i = 0; i < getCars().length; i++) {
            if (getCars()[i] == car) {
                getCars()[i] = null;
                break;
            }
        }
        setFreePlaces(getFreePlaces() + 1);
    }

    @Override
    public Car[] allPlaces() {
        return getCars();
    }

    @Override
    public int freePlaces() {
        return getFreePlaces();
    }
}
