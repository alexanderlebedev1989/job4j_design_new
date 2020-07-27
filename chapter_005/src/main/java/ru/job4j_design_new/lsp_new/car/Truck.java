package ru.job4j_design_new.lsp_new.car;

public class Truck extends Car {

    private int sizeNonStandardPlace = 2;
    private boolean nonStandardParking = true;

    public Truck(String name, double weight) {
        super(name, weight);
    }

    @Override
    public int getSizeNonStandardPlace() {
        return sizeNonStandardPlace;
    }

    @Override
    public boolean isNonStandardParking() {
        return nonStandardParking;
    }
}
