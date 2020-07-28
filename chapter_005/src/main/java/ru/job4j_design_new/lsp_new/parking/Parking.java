package ru.job4j_design_new.lsp_new.parking;

import ru.job4j_design_new.lsp_new.car.Vehicle;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Parking implements IParking {

    private final static int CAR_SIZE = 1;

    private List<Vehicle> cars = new ArrayList();
    private List<Vehicle> trucks = new ArrayList<>();

    private final int carsPlaces;
    private final int trucksPlaces;

    public Parking(int carsPlaces, int trucksPlaces) {
        this.carsPlaces = carsPlaces;
        this.trucksPlaces = trucksPlaces;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        if (!canPark(vehicle)) {
            throw new IllegalArgumentException();
        }
        if (vehicle.size() == CAR_SIZE) {
            cars.add(vehicle);
        } else {
            if (trucks.size() < trucksPlaces) {
                trucks.add(vehicle);
            } else {
                IntStream.range(0, vehicle.size()).forEach(t -> cars.add(vehicle));
            }
        }
        return true;
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        if (vehicle.size() == CAR_SIZE && cars.size() == carsPlaces) {
            return false;
        }
        if (vehicle.size() != CAR_SIZE && (trucks.size() == trucksPlaces
                && vehicle.size() > (carsPlaces - cars.size()))) {
            return false;
        }
        return true;
    }

    @Override
    public Vehicle retrieve(String number) {
        List<Vehicle> iCars = cars.stream().filter(v -> v.number().equals(number)).collect(Collectors.toList());
        if (iCars.size() > 0 && cars.removeAll(iCars)) {
            return iCars.get(0);
        }
        List<Vehicle> iTrucks = trucks.stream().filter(v -> v.number().equals(number)).collect(Collectors.toList());
        if (iTrucks.size() > 0 && trucks.removeAll(iTrucks)) {
            return iTrucks.get(0);
        }
        return null;
    }
}
