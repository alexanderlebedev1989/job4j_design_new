package ru.job4j_design_new.lsp_new.car;

public class Bus extends Car {

    private int sizeNonStandardPlace = 3;
    private boolean nonStandardParking = true;

    public Bus(String name, double weight) {
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
