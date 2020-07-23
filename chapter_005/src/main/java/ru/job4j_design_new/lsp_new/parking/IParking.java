package ru.job4j_design_new.lsp_new.parking;

public interface IParking extends IParkingInfo {
    boolean accept(Car car);
    void add(Car car, int numberPlace);
    void clear(Car car);
}
