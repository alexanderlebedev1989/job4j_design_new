package ru.job4j_design_new.lsp_new.car;

public class Car {

    private String name;
    private double weight;
    private boolean nonStandardParking = false;
    private boolean suitableParking = true;
    private int sizeNonStandardPlace;


    public Car(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }


    public boolean isNonStandardParking() {
        return nonStandardParking;
    }

    public int getSizeNonStandardPlace() {
        return sizeNonStandardPlace;
    }

    public boolean isSuitableParking() {
        return suitableParking;
    }

    public void setSuitableParking(boolean suitableParking) {
        this.suitableParking = suitableParking;
    }
}