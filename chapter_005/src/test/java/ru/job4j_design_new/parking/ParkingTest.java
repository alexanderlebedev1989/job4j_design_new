package ru.job4j_design_new.parking;

import org.junit.Test;
import ru.job4j_design_new.lsp_new.car.Bus;
import ru.job4j_design_new.lsp_new.car.Car;
import ru.job4j_design_new.lsp_new.car.CarPassenger;
import ru.job4j_design_new.lsp_new.car.Truck;
import ru.job4j_design_new.lsp_new.manager.IManager;
import ru.job4j_design_new.lsp_new.new_service.TwoParkingPlaces;
import ru.job4j_design_new.lsp_new.new_service.Place;
import ru.job4j_design_new.lsp_new.parking.IParking;
import ru.job4j_design_new.lsp_new.service.Service;
import ru.job4j_design_new.lsp_new.manager.Manager;
import ru.job4j_design_new.lsp_new.parking.ParkingCars;
import ru.job4j_design_new.lsp_new.parking.ParkingTrucks;
import ru.job4j_design_new.lsp_new.service.ServiceFindPlaces;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void standardPlace() {
        Car[] cars = new Car[2];
        Service service = new ServiceFindPlaces();
        int result = service.getPlace(cars);
        int expected = 0;
        assertThat(result, is(expected));
    }


    @Test
    public void whenGetNonStandardPlace() {
        Car[] cars = new Car[2];
        Place twoPlaces = new TwoParkingPlaces();
        int[] result = twoPlaces.getPlaces(cars);
        int[] expected = {0, 1};
        assertThat(result, is(expected));
    }

    @Test
    public void whenFindNonStandardPlace() {
        Car[] cars = new Car[2];
        Car truck = new Truck("ГАЗ", 2.0);
        Service service = new ServiceFindPlaces();
        int[] result = service.findPlace(truck, cars);
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
        Car bmw = new CarPassenger("BMW", 1.3);
        Car truck = new Truck("Газель", 1.6);
        IParking cars = new ParkingCars(1);
        IParking trucks = new ParkingTrucks(1);
        Service service = new ServiceFindPlaces();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(bmw);
        assertThat(cars.freePlaces(), is(0));
        manager.givePlace(truck);
        assertThat(trucks.freePlaces(), is(0));
    }

    @Test
    public void takePlace() {
        Car car = new CarPassenger("BMW", 1.3);
        Car truck = new Truck("Газель", 1.6);
        IParking cars = new ParkingCars(1);
        IParking trucks = new ParkingTrucks(1);
        Service service = new ServiceFindPlaces();
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
        Car truck = new Truck("Газель", 1.6);
        Car bus = new Bus("Автобус", 3);
        IParking cars = new ParkingCars(5);
        IParking trucks = new ParkingTrucks(0);
        Service service = new ServiceFindPlaces();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(truck);
        manager.givePlace(bus);
        assertThat(cars.freePlaces(), is(0));
    }

    @Test
    public void whenTakeOnlyTrucks() {
        Car truck = new Truck("Газель", 1.6);
        Car bus = new Bus("Автобус", 3);
        IParking cars = new ParkingCars(5);
        IParking trucks = new ParkingTrucks(0);
        Service service = new ServiceFindPlaces();
        IManager manager = new Manager(cars, trucks, service);
        manager.givePlace(truck);
        assertThat(cars.freePlaces(), is(3));
        manager.givePlace(bus);
        assertThat(cars.freePlaces(), is(0));
        manager.takePlace(truck);
        assertThat(cars.freePlaces(), is(2));
        manager.takePlace(bus);
        assertThat(cars.freePlaces(), is(5));
    }
}