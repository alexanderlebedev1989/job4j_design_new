package ru.job4j_design_new.parking;

import org.junit.Test;
import ru.job4j_design_new.lsp_new.car.Car;
import ru.job4j_design_new.lsp_new.car.Truck;
import ru.job4j_design_new.lsp_new.car.Vehicle;
import ru.job4j_design_new.lsp_new.parking.IParking;
import ru.job4j_design_new.lsp_new.parking.Parking;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingTest {

    @Test
    public void add() {
        IParking parking = new Parking(5, 5);
        Vehicle car = new Car("Волга", "444");
        Boolean result = parking.add(car);
        assertThat(result, is(true));
    }

    @Test
    public void whenAddOnlyTruck() {
        IParking parking = new Parking(5, 1);
        Vehicle truck = new Truck("Газель", "777", 2);
        Vehicle truck1 = new Truck("Газ", "888", 3);
        Boolean result = parking.add(truck);
        assertThat(result, is(true));
        Boolean result1 = parking.add(truck1);
        assertThat(result1, is(true));
    }

    @Test
    public void retrieve() {
        IParking parking = new Parking(5, 1);
        Vehicle truck = new Truck("Газель", "777", 2);
        parking.add(truck);
        Vehicle result = parking.retrieve(truck.number());
        assertThat(result, is(new Truck("Газель", "777", 2)));
    }

    @Test
    public void canPark() {
        IParking parking = new Parking(5, 1);
        Vehicle truck = new Truck("Газель", "777", 2);
        Boolean result = parking.canPark(truck);
        assertThat(result, is(true));
    }

    @Test
    public void whenCanNotPark() {
        IParking parking = new Parking(0, 0);
        Vehicle truck = new Truck("Газель", "777", 2);
        Boolean result = parking.canPark(truck);
        assertThat(result, is(false));
    }
}