package ru.job4j_design_new.parking;

import org.junit.Test;
import ru.job4j_design_new.lsp_new.car.Car;
import ru.job4j_design_new.lsp_new.manager.IManager;
import ru.job4j_design_new.lsp_new.parking.IParking;
import ru.job4j_design_new.lsp_new.service.Service;
import ru.job4j_design_new.lsp_new.service.ServiceFindPlace;
import ru.job4j_design_new.lsp_new.manager.Manager;
import ru.job4j_design_new.lsp_new.parking.ParkingCars;
import ru.job4j_design_new.lsp_new.parking.ParkingTrucks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void standardPlace() {
        Car[] cars = new Car[2];
        Service service = new ServiceFindPlace();
        int result = service.standardPlace(cars);
        int expected = 0;
        assertThat(result, is(expected));
    }

    @Test
    public void NonStandardPlace() {
        Car[] cars = new Car[2];
        Service service = new ServiceFindPlace();
        int[] result = service.nonStandardPlace(cars);
        int[] expected = {0, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void accept() {
        Car car = new Car("BMW", 1.3);
        IParking parking = new ParkingCars(10);
        boolean result = parking.accept(car);
        assertThat(result, is(true));
    }

    @Test
    public void add() {
        Car car = new Car("BMW", 1.3);
        IParking parking = new ParkingCars(10);
        parking.add(car, 0);
        int result = parking.freePlaces();
        assertThat(result, is(9));
    }

    @Test
    public void clear() {
        Car car = new Car("BMW", 1.3);
        IParking parking = new ParkingCars(10);
        parking.add(car, 0);
        parking.clear(car);
        int result = parking.freePlaces();
        assertThat(result, is(10));
    }

    @Test
    public void freePlaces() {
        IParking parking = new ParkingCars(10);
        int result = parking.freePlaces();
        assertThat(result, is(10));
    }

    @Test
    public void allPlaces() {
        IParking parking = new ParkingCars(10);
        Car[] result = parking.allPlaces();
        Car[] expected = new Car[10];
        assertThat(result, is(expected));
    }

    @Test
    public void givePlace() {
        Car car = new Car("BMW", 1.3);
        Car truck = new Car("Газель", 1.6);
        IParking cars = new ParkingCars(1);
        IParking trucks = new ParkingTrucks(1);
        Service service= new ServiceFindPlace();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(car);
        assertThat(cars.freePlaces(), is(0));
        manager.givePlace(truck);
        assertThat(trucks.freePlaces(), is(0));
    }
    @Test
    public void takePlace() {
        Car car = new Car("BMW", 1.3);
        Car truck = new Car("Газель", 1.6);
        IParking cars = new ParkingCars(1);
        IParking trucks = new ParkingTrucks(1);
        Service service= new ServiceFindPlace();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(car);
        manager.takePlace(car);
        assertThat(cars.freePlaces(), is(1));
        manager.givePlace(truck);
        manager.takePlace(truck);
        assertThat(trucks.freePlaces(), is(1));
    }

    @Test
    public void whenGiveOnlyTrucks() {
        Car truck1 = new Car("Газель", 1.6);
        IParking cars = new ParkingCars(2);
        IParking trucks = new ParkingTrucks(0);
        Service service= new ServiceFindPlace();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(truck1);
        assertThat(cars.freePlaces(), is(0));
    }

    @Test
    public void whenCombinedParking() {
        Car bmw = new Car("BMW", 1.3);
        Car gazel = new Car("Газель", 1.6);
        Car gaz = new Car("ГАЗ", 2.5);
        Car lada = new Car("Lada", 1.0);
        Car truck5 = new Car("Урал", 3.0);
        Car truck6 = new Car("ГАЗ", 2.5);
        IParking cars = new ParkingCars(10);
        IParking trucks = new ParkingTrucks(2);
        Service service= new ServiceFindPlace();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(bmw);
        assertThat(cars.freePlaces(), is(9));
        manager.givePlace(gazel);
        assertThat(trucks.freePlaces(), is(1));
        manager.givePlace(gaz);
        assertThat(trucks.freePlaces(), is(0));
        manager.givePlace(lada);
        assertThat(cars.freePlaces(), is(8));
        manager.givePlace(truck5);
        assertThat(cars.freePlaces(), is(6));
        manager.takePlace(bmw);
        assertThat(cars.freePlaces(), is(7));
        manager.takePlace(lada);
        assertThat(cars.freePlaces(), is(8));
        manager.givePlace(truck6);
        assertThat(cars.freePlaces(), is(6));
        manager.takePlace(gazel);
        assertThat(trucks.freePlaces(), is(1));
    }
}
